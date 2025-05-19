import { ref } from "vue";
import PersonDto from "@/models/PersonDto";

export const genericStudent = ref<PersonDto>({
	name: 'John Doe',
	istId: '110283',
	email: 'johndoe@tecnico.ulisboa.pt',
	type: 'STUDENT'
});