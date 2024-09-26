const routes = [
  {
    path: "/",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      {
        path: "dashboard",
        component: () => import("pages/MainDashboard.vue"),
      },
      {
        path: "history",
        component: () => import("pages/FinancialHistory.vue"),
      },
      {
        path: "calculator",
        component: () => import("pages/BudgetCalculator.vue"),
      },
      {
        path: "add",
        component: () => import("pages/AddRecord.vue"),
      },
    ],
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: "/:catchAll(.*)*",
    component: () => import("pages/ErrorNotFound.vue"),
  },
];

export default routes;
