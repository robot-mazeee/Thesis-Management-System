# Thesis Management System (TPMS)

**Thesis Management System (_TMS_)** is an internal platform that allows for the centralized management of the PhD student supervision process in a department, facilitating the organization and control of the different workflows related to the thesis and defense phases.

This subsystem allows the management of:

- **People**, including, for each individual, at least their name, IST ID, email, and their role in the system (staff, student, professor, coordinator, SC (Scientific Committee));

- **Workflows**, organized into two sections: thesis workflow and defense workflow, with stages associated with each.


## Workflows

### Thesis Workflow
- **Jury Proposal Submitted** – Submitted by the student  
- **Approved by SC** – Approved by the Scientific Committee  
- **Jury President Assigned** – Assigned by the coordinator  
- **Signed Document** – Signed by the coordinator  
- **Submitted to Fenix** – Submitted by the staff

### Defense Workflow
- **Defense Scheduled** – Scheduled by the coordinator  
- **Under Review** – If the current date is after the scheduled date  
- **Submitted to Fenix** – Once the grade is assigned by the coordinator


#### Example of Complete Workflows

An example of a complete thesis workflow:

- The _student_, on their page, selects the professors (from one (1) to five (5) professors) who will compose the thesis jury and submits the proposal, initiating the thesis workflow with the state **Jury Proposal Submitted**.
- The _SC_ (Scientific Committee) reviews the proposal and approves it (or not), changing the state to **Approved by SC**.
- The _coordinator_ reviews the approved proposal and selects one of the professors to be the jury president, changing the state to **Jury President Assigned**.
- The _coordinator_ downloads the document (a PDF file containing thesis and jury information), signs it, and uploads the signed document, changing the state to **Signed Document**.
- The _staff_ confirms the signed document has been submitted to Fenix (for implementation purposes, this submission is just a button that changes the state — no interaction with Fenix is required), changing the state to **Submitted to Fenix**.

Once the thesis workflow is complete, the defense workflow can begin:

- The _coordinator_ schedules the defense, initiating the defense workflow with the state **Defense Scheduled**.
- When the current date is later than the scheduled date, the state changes to **Under Review**.
- The _coordinator_ assigns a grade to the student, changing the state to **Submitted to Fenix**.


## Goal functionalities

- Display all TMS members in a quick-access table, containing at least name, IST ID, email, and role;
- Add new people to the system;
- Update and remove existing people;
- Assign roles in the frontend: staff, student, professor, coordinator, SC;
- Display all students in a quick-access table, containing at least name, IST ID, email, and the state of the thesis/defense workflow;
- Filter students by thesis/defense workflow state or other relevant fields;
- View each student’s individual page, including:
  - Personal information section;
  - Thesis workflow;
  - Defense workflow;
- Update workflow states according to the described steps;
- Revert workflow states according to the described steps;

## Aditional functionalities

- A statistics page showing system data, such as the number of students per thesis/defense workflow state, average grades, etc.;
- Detailed logs of actions performed in the system, such as submissions, approvals, and workflow updates;
- Student authentication on the backend;
- A homepage displaying user data:
  - Real data for the **student** role (as this is the only role requiring account creation and authentication);
  - Generic data for all other roles;
- Email sending system using [mailcrab](https://github.com/tweedegolf/mailcrab);

---

_Note that the situation described in this assignment has been simplified and does not necessarily reflect reality._


## Goal for Database

Include a `populate.sql` file with a database *dump* that allows the database to be populated with data for testing and evaluation. This file can be generated using [`pg_dump`](https://www.postgresql.org/docs/current/app-pgdump.html), for example with the command:  
`pg_dump -U <USER> -p <PORT> > populate.sql`


## Recursos Potencialmente Úteis

- [https://vuetifyjs.com/en/](https://vuetifyjs.com/en/)
- [https://spring.io/guides](https://spring.io/guides)
- [https://www.javatpoint.com/spring-boot-architecture](https://www.javatpoint.com/spring-boot-architecture)

