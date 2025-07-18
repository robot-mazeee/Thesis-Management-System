export const getColorByStatus = (status: string) => {
  switch (status) {
    case "APPROVED_BY_SC":
      return "purple";
    case "JURI_PRESIDENT_ATTRIBUTED":
      return "red";
    case "DOCUMENT_SIGNED":
      return "green";
    case "SUBMITTED_TO_FENIX":
      return "yellow";
    default:
      return "blue"
  }
};

export const translateStatus = (status: string) => {
  switch (status) {
    case "JURI_PROPOSAL_SUBMITTED":
      return "Juri Proposal Submitted"
    case "APPROVED_BY_SC":
      return "Approved By SC";
    case "JURI_PRESIDENT_ATTRIBUTED":
      return "Juri President Attributed";
    case "DOCUMENT_SIGNED":
      return "Document Signed";
    case "SUBMITTED_TO_FENIX":
      return "Submitted to Fenix";
    default:
      return "No workflows initiated";
  }
};

export const translateStatusToIndex = (status: string) => {
  switch (status) {
    case "JURI_PROPOSAL_SUBMITTED":
      return 0
    case "APPROVED_BY_SC":
      return 1;
    case "JURI_PRESIDENT_ATTRIBUTED":
      return 2;
    case "DOCUMENT_SIGNED":
      return 3;
    case "SUBMITTED_TO_FENIX":
      return 4;
    default:
      return -1;
  }
};