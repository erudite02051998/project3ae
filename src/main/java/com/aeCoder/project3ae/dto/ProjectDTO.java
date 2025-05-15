package com.aeCoder.project3ae.dto;

import com.aeCoder.project3ae.entity.Category;
import com.aeCoder.project3ae.entity.Project;

public class ProjectDTO {
	private Project project;
    private Category category;

    public ProjectDTO(Project project, Category category) {
        this.project = project;
        this.category = category;
    }

    public Project getProject() {
        return project;
    }

    public Category getCategory() {
        return category;
    }
}
