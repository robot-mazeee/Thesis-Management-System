import RemoteServices from "@/services/RemoteService";
import WorkflowDto from "@/models/WorkflowDto";

export async function getWorkflows() {
	console.log("getting workflows");
	try {
		const workflows = await RemoteServices.getWorkflows();
        console.log("Fetched workflows: ", workflows);
		return workflows;
	} catch (error) {
		console.error("Error getting workflows: ", error);
	}
}

export function checkStatus(status: string) {
    console.log(status)
    switch(status) {
        case "APPROVED_BY_ADMIN":
            return true;
        case "JURI_PRESIDENT_ATTRIBUTED":
            return true;
        case "DOCUMENT_SIGNED":
            return true;
        case "SUBMITTED_TO_FENIX":
            return true;
        default:
            return false;
    }
}

export async function updateStatus(workflow: WorkflowDto, status: string) {
    console.log("Sending workflow for approval:", workflow);
    try {
        workflow.status = status;
        console.log(workflow.status);
        const response = await RemoteServices.updateWorkflow(workflow);
        console.log("Server Response:", response);
        getWorkflows();
    } catch (error) {
        console.error("Approval failed:", error);
    }
}