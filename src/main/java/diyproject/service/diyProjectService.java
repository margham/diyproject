package diyproject.service;

import diyproject.dao.diyprojectDao;
import diyproject.entity.Project;

public class diyProjectService {
	
	private diyprojectDao projectdao = new diyprojectDao();
	
	public Project addProject(Project project) {
		return projectdao.insertProject(project);
	}

}
