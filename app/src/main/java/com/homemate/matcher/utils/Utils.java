package com.homemate.matcher.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.firestore.DocumentSnapshot;
import com.homemate.matcher.models.SearchStatus;
import com.homemate.matcher.models.User;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Utils {

    public List<User> convertDocumentsToUserObject(List<DocumentSnapshot> documentSnapshots){
        ObjectMapper objectMapper = new ObjectMapper();
        return documentSnapshots.stream().map((doc) -> {
            Map<String, Object> docData = doc.getData();
            User user = new User(doc.getId(), "test", "test");
            for (String key: docData.keySet()) {
                switch (key){
                    case "firstName":
                        user.setFirstName((String) docData.get("firstName"));
                        break;
                    case "lastName":
                        user.setLastName((String) docData.get("firstName"));
                        break;
                    case "status":{
                        String status = (String)docData.get("status");
                        status = status.toUpperCase().replace("İ", "I");
                        user.setSearchStatus(SearchStatus.valueOf(status));
                    }
                        break;
//                    case "department":
//                        user.setDepartment();
//                        break;
                    case "distanceToCampus":
                        user.setDistanceToCampus(
                                    ((Long) docData.get("distanceToCampus")).doubleValue());
                        break;
                    case "hostingTime":
                        user.setHostingTime((Long) docData.get("hostingTime"));
                        break;
                    case "isVerified":
                        user.setVerified((Boolean) docData.get("isVerified"));
                        break;
                }
            }
            /*try {
                user = objectMapper.convertValue(doc.getData(), User.class);
            } catch (Exception e){
                System.err.println("User doc convert error");
                e.printStackTrace();
            }*/
            return user;
        }).collect(Collectors.toList());
    }

}
