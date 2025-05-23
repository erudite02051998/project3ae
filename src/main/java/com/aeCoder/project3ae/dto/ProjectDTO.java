package com.aeCoder.project3ae.dto;

import java.util.List;

import com.aeCoder.project3ae.entity.Category;
import com.aeCoder.project3ae.entity.Project;
import com.aeCoder.project3ae.entity.Technical;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDTO {
	private Project project;
    private List<Category> categories;
    private List<Technical> technicals;

    public ProjectDTO(Project project, List<Category> categories, List<Technical> technicals) {
        this.project = project;
        this.categories = categories;
        this.technicals = technicals;
    }
}
