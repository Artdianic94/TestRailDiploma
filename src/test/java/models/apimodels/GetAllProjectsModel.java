package models.apimodels;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@lombok.Data
@ToString
@Builder
@EqualsAndHashCode
@Jacksonized
public class GetAllProjectsModel {
    private int offset;
    private int limit;
    private int size;
    private Links _links;
    private List<Projects> projects;

    public List<Integer> getMapOfProjects(List<Projects> projectsN) {
        Map<Integer, Projects> myMapOfProjects = projects.stream().collect(Collectors.toMap(Projects::getId, projects -> projects));
        List<Integer> idCount = new ArrayList<>();
        for (int i = 0; i < myMapOfProjects.keySet().size(); i++) {
            idCount.add(myMapOfProjects.keySet().stream().toList().get(i));
        }
        return idCount;
    }
}
