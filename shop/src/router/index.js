import { createRouter, createWebHistory } from "vue-router"

const routes = [
  {
    path: '/',
    name: 'Main',
    component: () => import("@/views/Main.vue")

  },
  {
    path: '/login',
    name: 'Login',
    component: () => import("@/views/Login.vue")
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import("@/views/Register.vue")
  },

  {
    path: '/goodsByWord',
    name: 'GoodsByWord',
    component: () => import("@/views/GoodsByWord.vue")
  },
  {
    path: '/goodsDetails',
    name: 'GoodsDetails',
    component: () => import("@/views/GoodsDetails.vue")
  },

  {
    path: '/collections',
    name: 'Collections',
    component: () => import("@/views/Collections.vue")
  },

  {
    path: '/shoppingCart',
    name: 'ShoppingCart',
    component: () => import("@/views/ShoppingCart.vue")
  },
  {
    path: '/myOrders',
    name: 'MyOrders',
    component: () => import("@/views/MyOrders.vue")
  },

  {
    path: '/payGateWay',
    name: 'PayGateWay',
    component: () => import("@/views/PayGateWay.vue")
  },
  {
    path: '/admin',
    name: 'Admin',
    component: () => import("@/views/Admin.vue"),
    children: [
      {
        path: '/adminGoods',
        name: 'AdminGoods',
        component: () => import("@/views/AdminGoods.vue")
      },

      {
        path: '/adminOrders',
        name: 'AdminOrders',
        component: () => import("@/views/AdminOrders.vue")
      },

      {
        path: '/adminGType',
        name: 'AdminGType',
        component: () => import("@/views/AdminGType.vue")
      },

      {
        path: '/adminStock',
        name: 'AdminStock',
        component: () => import("@/views/AdminStock.vue")
      },
      {
        path: '/adminSales',
        name: 'AdminSales',
        component: () => import("@/views/AdminSales.vue")
      },
      {
        path: '/adminSCount',
        name: 'AdminSCount',
        component: () => import("@/views/AdminSCount.vue")
      },





    ]
  },



]


const router = createRouter({
  history: createWebHistory(),
  routes
})
export default router

