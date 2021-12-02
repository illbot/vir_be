package com.example.kepbongeszo_be.Controller;

import com.example.kepbongeszo_be.Controller.Request.UploadRequest;
import com.example.kepbongeszo_be.Controller.Response.HelloWorld;
import com.example.kepbongeszo_be.Controller.Response.MessageResponse;
import com.example.kepbongeszo_be.Controller.Response.PictureResponse;
import com.example.kepbongeszo_be.Controller.Response.ResponseMessage;
import com.example.kepbongeszo_be.Model.Picture;
import com.example.kepbongeszo_be.Model.User;
import com.example.kepbongeszo_be.Repository.UserRepository;
import com.example.kepbongeszo_be.Service.PictureService;
import com.example.kepbongeszo_be.Service.PictureStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/picture")
public class PictureController {

    @Autowired
    PictureService pictureService;

    @Autowired
    PictureStorageService storageService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/uploadData")
    public MessageResponse upload(@RequestBody UploadRequest request){
        if (pictureService.addPicture(request)) {
            return new MessageResponse("Sikeres!");
        } else {
            return new MessageResponse("Valami hiba van!");
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.save(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<PictureResponse>> getListFiles(Authentication auth) {
        String username=auth.getName();
        User user=userRepository.findByUsername(username).orElse(null);
        if(user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
        }

        List<PictureResponse> fileInfos = new ArrayList<>();
        pictureService.loadAll(user).forEach(picture -> {
            PictureResponse result = new PictureResponse();

            result.setId(picture.getId());
            result.setDescription(picture.getDescription());
            result.setName(picture.getName());
            result.setType(picture.getType());
            result.setUploader(picture.getUploader().getUsername());
            result.setDisplayName(picture.getDisplayName());

            fileInfos.add(result);
        });

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @RequestMapping("/get")
    public HelloWorld home(){
        return new HelloWorld();
    }
}
