export const getColorByType = (type: string) => {
  switch (type) {
    case "COORDINATOR":
      return "purple";
    case "STAFF":
      return "red";
    case "PROFESSOR":
      return "blue";
    case "ADMIN":
      return "yellow";
    case "STUDENT":
      return "grey";
    default:
      return "green"
  }
};

export const typeMappings = {
	Coordinator: 'COORDINATOR',
	Staff: 'STAFF',
	Student: 'STUDENT',
	Professor: 'PROFESSOR',
	Admin: 'ADMIN'
}

export const translateType = (type: string) => {
  switch (type) {
    case "COORDINATOR":
      return "Coordinator";
    case "STAFF":
      return "Staff";
    case "PROFESSOR":
      return "Professor";
    case "ADMIN":
      return "Admin";
    case "STUDENT":
      return "Student";
    default:
      return "Default";
  }
};
