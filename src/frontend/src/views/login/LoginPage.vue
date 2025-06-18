<template>
    <h1>Welcome to DEI-PMS!</h1>
    <div class="pa-4 d-flex flex-column align-center justify-center">
        <v-card title="Login" width="50%" class="pb-6 mb-6">
            <v-card-text>
                <v-text-field prepend-icon="mdi-account-outline"
                    placeholder=""
                    label="Email"
                    required>
                </v-text-field>
                <v-text-field prepend-icon="mdi-lock-outline"
                    label="Password"
                    required
                >
                </v-text-field>
            </v-card-text>
    
            <v-card-actions class="d-flex justify-center">
                <v-btn
                    color="primary"
                    text="Login"
                    variant="tonal"
                    @click="
                        dialog = false,
                        login()
                    "
                ></v-btn>
            </v-card-actions>

        </v-card>

        <div class="pa-4 d-flex justify-center">
            <v-btn
                color="secondary"
                text="Or Create Account"
                variant="tonal"
                @click="
                    dialog = false,
                    createAccount()
                "
            ></v-btn>
        </div>
    </div>
</template>
  
<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useStudentStore } from '../../stores/student';
import router from '../../router';
import { genericStudent } from '../genericStudent';

const dialog = ref(false);
const studentStore = useStudentStore();

const newStudent = genericStudent;

onMounted(() => {
    dialog.value = true;
})

async function login() {
    try {
        studentStore.login(newStudent.value);
        router.push("/home");
		console.log('Logged in as: ', newStudent.value);
	} catch (error) {
		console.error("Error logging in: ", error);
	}
}

function createAccount() {}

</script>