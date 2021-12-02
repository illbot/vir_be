package com.example.kepbongeszo_be.Service;

import com.example.kepbongeszo_be.Controller.Request.UploadRequest;
import com.example.kepbongeszo_be.Model.ERole;
import com.example.kepbongeszo_be.Model.Picture;
import com.example.kepbongeszo_be.Model.Role;
import com.example.kepbongeszo_be.Model.User;
import com.example.kepbongeszo_be.Repository.PictureRepository;
import com.example.kepbongeszo_be.Repository.RoleRepository;
import com.example.kepbongeszo_be.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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

        //String encodedDataUrl = Base64.getEncoder().encodeToString(request.getDataURL().getBytes());

        //pic.setDataURL(dataUrlByte);

        pic.setDescription(request.getDescription());
        pic.setName(request.getName());
        pic.setType(request.getType());
        pic.setVisibilityList(roleSet);
        pic.setDisplayName(request.getDisplayName());

        pictureRepository.save(pic);

        return pictureRepository.exists(Example.of(pic));
    }

    public List<Picture> loadAll(User user){
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
}
