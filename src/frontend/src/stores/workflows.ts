import { defineStore } from "pinia";
import WorkflowDto from "@/models/WorkflowDto";
import RemoteServices from "@/services/RemoteService";

export const useWorkflowStore = defineStore('workflow', {
    state: () => ({
        workflows: [] as WorkflowDto[],
        loading: false as boolean
    }),
    actions: {
        async fetchWorkflows() {
            console.log("getting workflows");
            this.loading = true;
            try {
                const data = await RemoteServices.getWorkflows();
                console.log("Fetched workflows: ", data);
                this.workflows = data;
            } catch (error) {
                console.error("Error getting workflows: ", error);
            } finally {
                this.loading = false;
            }
        },

        async updateStatus(workflow: WorkflowDto, status: string) {
            try {
                workflow.status = status;
                await RemoteServices.updateWorkflow(workflow);
                await this.fetchWorkflows();
            } catch (error) {
                console.error("Workflow status update failed:", error);
            }
        }
    }
});