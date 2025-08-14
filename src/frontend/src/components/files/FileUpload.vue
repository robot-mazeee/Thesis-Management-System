<template>
	<v-container>
		<v-file-input
			label="Upload File"
			@change="onFileSelect"
			accept="*.pdf"
			show-size
		></v-file-input>
		<v-btn @click="uploadFile" :disabled="!selectedFile">Upload File</v-btn>
	</v-container>
</template>

<script setup lang="ts">
import { ref } from "vue";
import WorkflowDto from "../../models/WorkflowDto";
import { useWorkflowStore } from "../../stores/workflows";

const props = defineProps<{
  workflow: WorkflowDto
}>();

const selectedFile = ref<File | null>(null);
const workflowStore = useWorkflowStore();

const onFileSelect = (event: Event) => {
	const input = event.target as HTMLInputElement;
	if (input.files && input.files[0]) {
		selectedFile.value = input.files[0];
	}
};

const uploadFile = async () => {
	if (!selectedFile.value) return;

	const formData = new FormData();
	formData.append("file", selectedFile.value);

	try {
		const workflow: WorkflowDto = props.workflow;
		await workflowStore.updateStatus(workflow, "DOCUMENT_SIGNED");
	} catch (error) {
		console.error("Upload failed:", error);
	}
};
</script>
