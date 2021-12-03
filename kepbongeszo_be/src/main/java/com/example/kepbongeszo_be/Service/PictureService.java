package com.example.kepbongeszo_be.Service;

import com.example.kepbongeszo_be.Controller.Request.ChangeVisibilityRequest;
import com.example.kepbongeszo_be.Controller.Request.UploadRequest;
import com.example.kepbongeszo_be.Controller.Response.AdminPictureResponse;
import com.example.kepbongeszo_be.Controller.Response.PictureResponse;
import com.example.kepbongeszo_be.Controller.Response.ResponseMessage;
import com.example.kepbongeszo_be.Model.ERole;
import com.example.kepbongeszo_be.Model.Picture;
import com.example.kepbongeszo_be.Model.Role;
import com.example.kepbongeszo_be.Model.User;
import com.example.kepbongeszo_be.Repository.PictureRepository;
import com.example.kepbongeszo_be.Repository.RoleRepository;
import com.example.kepbongeszo_be.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.util.*;

@Service
public class PictureService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PictureRepository pictureRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PictureStorageService pictureStorageService;

    public boolean addPicture(UploadRequest request){
        User uploader = userRepository.findById(request.getUploader()).orElse(null);
        Picture pic = new Picture();

        pic.setUploader(uploader);

        Set<Role> roleSet = new HashSet<>();

        request.getVisibility_list().forEach( rolename ->{
            Optional<Role> role = roleRepository.findByName(ERole.valueOf(rolename));
            role.ifPresent(actual ->{
                roleSet.add(actual);
            });
        });

        pic.setDescription(request.getDescription());
        pic.setName(request.getName());
        pic.setType(request.getType());
        pic.setVisibilityList(roleSet);
        pic.setDisplayName(request.getDisplayName());

        pictureRepository.save(pic);

        return pictureRepository.exists(Example.of(pic));
    }

    public List<Picture> loadAllByRole(User user){
        List<Picture> result = new ArrayList<>();
        Set<Picture> picSet = new HashSet<>();
        user.getRoles().forEach(role -> {
            pictureRepository.findAllByRole(role).forEach(picture -> {
                picSet.add(picture);
            });
        });
        picSet.forEach(picture -> {
            result.add(picture);
        });
        return result;
    }

    public List<AdminPictureResponse> getAll(){
        List<AdminPictureResponse> result = new ArrayList<>();
        pictureRepository.findAll().forEach(picture -> {
            AdminPictureResponse pr = new AdminPictureResponse();

            pr.setDisplayName(picture.getDisplayName());
            pr.setId(picture.getId());
            pr.setUploader(picture.getUploader().getUsername());
            pr.setName(picture.getName());
            pr.setDescription(picture.getDescription());
            pr.setAvailability(picture.getVisibilityList());
            pr.setType(picture.getType());

            result.add(pr);
        });
        return result;
    }

    public ResponseEntity<ResponseMessage> changeVisibility(ChangeVisibilityRequest cvr) {
        Picture pic = pictureRepository.findById(cvr.getPictureId()).orElse(null);
        Role role = roleRepository.findByName(ERole.ROLE_USER).orElse(null);
        Set<Role> pvl = pic.getVisibilityList();

        if (cvr.isUserVisibility()) {
            pvl.add(role);
        } else {
            pvl.remove(role);
        }

        pic.setVisibilityList(pvl);

        return pictureRepository.save(pic) != null ?
                ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Change successfull!")) :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Couldn't save changes!"));
    }

    public ResponseEntity<ResponseMessage> delete(Long pictureId) {
        Picture pic = pictureRepository.findById(pictureId).orElse(null);
        String filename = pic.getName();

        if(pictureStorageService.delete(filename)){
            pictureRepository.delete(pic);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Delete successfull!"));
        } else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Couldn't delete picture!"));
        }
    }
}
