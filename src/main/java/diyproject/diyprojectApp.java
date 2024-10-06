package diyproject;

import java.math.BigDecimal;
import diyproject.service.diyProjectService;
import diyproject.exception.*;
import diyproject.entity.Project;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import diyproject.entity.Project;

public class diyprojectApp {
	
	private Scanner scanner = new Scanner(System.in);
	private diyProjectService projectService = new diyProjectService();

//@formatter:off
	private List<String> operations = List.of(
			"1) Add a project."
);			
//@formatter:on

	public static void main(String[] args) {
		new diyprojectApp().processUserSelections();
		
	}

	
	private void processUserSelections() {
			// display menu selections, get a selection from the user, 
		    // and act on the selection.
		boolean done = false;
		
		while(!done) {
			try {
				int selection = getUserSelection();
				
				switch (selection) {
				case -1:
					done = exitMenu();
					break;
				case 1:
					createProject();
					break;
				default: 
					System.out.println("\n" + selection + " is not a valid selection :( . Try again.");
					break;
				}
			}
			catch(Exception e) {
				System.out.println("\nError: " + e + " Try again.");
			}
		}	
	}
	
	private void createProject() {
		String projectName = getStringInput("Enter the project name");
		BigDecimal estimatedHours = getDecimalInput("Enter the estimated hours");
		BigDecimal actualHours = getDecimalInput("Enter the actual hours");
		Integer difficulty = getIntInput("Enter the project difficulty (1-5)");
		String notes = getStringInput("Enter the project notes");
		
		Project project = new Project();
		
		project.setProjectName(projectName);
		project.setEstimatedHours(estimatedHours);
		project.setActualHours(actualHours);
		project.setDifficulty(difficulty);
		project.setNotes(notes);
		
		Project dbProject = projectService.addProject(project);
		System.out.println("You have successfully created project: " + dbProject);
	}
	
	private BigDecimal getDecimalInput(String prompt) {
	String input = getStringInput(prompt);
		
		if(Objects.isNull(input)) {
			return null;
		}
		try {
			return new BigDecimal(input).setScale(2);
		}
		catch(NumberFormatException e) {
			throw new DBexception(input + " is not a valid decimal number.");
			}
	}
	
	private boolean exitMenu() {
		System.out.println("Exiting the menu.");
		return true;
	}
	
	private int getUserSelection() {
		printOperations();
		
		Integer input = getIntInput("Enter a menu selection");
		
		return Objects.isNull(input) ? -1 : input;
	}
	
	private Integer getIntInput(String prompt) {
		String input = getStringInput(prompt);
		
		if(Objects.isNull(input)) {
			return -1;
		}
		try {
			return Integer.valueOf(input);
		}
		catch(NumberFormatException e) {
			throw new DBexception(input + " is not a valid number.");
			}
		}
	
	private String getStringInput(String prompt) {
		System.out.print(prompt + ": ");
		String input = scanner.nextLine();
		
		return input.isBlank() ? null : input.trim();
	}
	
	private void printOperations() {
		System.out.print("\nThese are the available selections. Press the Enter key to quit:");
		for(String operation : operations) {
			System.out.println("\n   " + operation);
		}
	}

	
}
