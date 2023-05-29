package com.homemate.matcher.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.firestore.DocumentSnapshot;
import com.homemate.matcher.models.Department;
import com.homemate.matcher.models.SearchStatus;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Utils {

    public String getStatusViews(String status) {
        String result = "";
        switch (status){
            case "not_in_search":
                result = "Arayışta değil";
                break;
            case "in_search_of_mate":
                result = "Arkadaş arayışında";
                break;
            case "in_search_of_home":
                result = "Ev arayışında";
                break;
        }
        return result;
    }
    public SearchStatus getStatusFromViewa(String statusView) {
        SearchStatus result = SearchStatus.IN_SEARCH_OF_HOME;
        switch (statusView){
            case "Arayışta değil":
                result = SearchStatus.NOT_IN_SEARCH;
                break;
            case "in_search_of_mate":
                result = SearchStatus.IN_SEARCH_OF_MATE;
                break;
            case "in_search_of_home":
                result = SearchStatus.IN_SEARCH_OF_HOME;
                break;
        }
        return result;
    }

    public List<Department> convertDocumentToDepartmentObject(List<DocumentSnapshot> documentSnapshots){
        ObjectMapper objectMapper = new ObjectMapper();
        return documentSnapshots.stream().map((doc) -> {
            Map<String, Object> docData = doc.getData();
            return new Department(doc.getId()
                                                , (String) docData.get("name")
                                                , (String) docData.get("şabel")
                                                , (Long) docData.get("code"));
        }).collect(Collectors.toList());
    }

}
