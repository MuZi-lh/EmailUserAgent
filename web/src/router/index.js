import { createRouter, createWebHashHistory } from "vue-router";

const routes = [
  {
    path: "/",
    name: "Login",
    component: () => import("@/components/Login"),
  },
  {
    path: "/operationFrame",
    name: "OperationFrame",
    component: () => import("../components/Operation/OperationFrame"),
    children: [
      {
        path: '/sendEmail',
        name: 'SendEmail',
        component: () => import("@/components/Operation/SendEmail")
      },
      {
        path: '/fetchEmail',
        name: 'FetchEmail',
        component: () => import("@/components/Operation/FetchEmail")
      }
    ]
  }
];

export const router = createRouter({
  base:'/dist',
  mode:'history',
  history: createWebHashHistory(),
  routes: routes
});