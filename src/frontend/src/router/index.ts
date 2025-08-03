import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import PeopleView from '@/views/people/PeopleView.vue'
import StatisticsView from '@/views/statistics/StatisticsView.vue'
import StudentProfile from '@/views/student/StudentProfile.vue'
import WorkflowsView from '@/views/workflows/WorkflowsView.vue'
import LoginPage from '@/views/login/LoginPage.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/home',
      name: 'home',
      component: HomeView
    },
    {
      path: '/people',
      name: 'people',
      component: PeopleView
    },
    {
      path: '/statistics',
      name: 'statistics',
      component: StatisticsView
    },
    {
      path: '/workflows',
      name: 'workflows',
      component: WorkflowsView
    },
    { 
      path: "/people/:id",
      name: "student-profile",
      component: StudentProfile
    },
    { 
      path: "/",
      name: "login-page",
      component: LoginPage
    }
  ]
})

export default router
