import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', redirect: '/login' },
    {
      path: '/manager',
      component: () => import('@/views/Manager.vue'),
      children: [
        { path: 'home', meta: { name: '系统首页' }, component: () => import('@/views/manager/Home.vue'),  },
        { path: 'admin', meta: { name: '管理员信息' }, component: () => import('@/views/manager/Admin.vue'), },
        { path: 'notice', meta: { name: '系统公告' }, component: () => import('@/views/manager/Notice.vue'), },
        { path: 'person', meta: { name: '个人资料' }, component: () => import('@/views/manager/Person.vue'), },
        { path: 'password', meta: { name: '修改密码' }, component: () => import('@/views/manager/Password.vue'), },
        { path: 'textbookType', meta: { name: '教材类型信息' }, component: () => import('@/views/manager/TextbookType.vue'), },
        { path: 'textbook', meta: { name: '教材信息' }, component: () => import('@/views/manager/Textbook.vue'), },
        { path: 'inventory', meta: { name: '库存信息' }, component: () => import('@/views/manager/Inventory.vue'), },
        { path: 'inStock', meta: { name: '入库信息' }, component: () => import('@/views/manager/InStock.vue'), },
        { path: 'classInfo', meta: { name: '班级信息' }, component: () => import('@/views/manager/ClassInfo.vue'), },
        { path: 'student', meta: { name: '学生信息' }, component: () => import('@/views/manager/Student.vue'), },
        { path: 'teacher', meta: { name: '教务员信息' }, component: () => import('@/views/manager/Teacher.vue'), },
        { path: 'outStock', meta: { name: '教材发放' }, component: () => import('@/views/manager/OutStock.vue'), },
        { path: 'feedback', meta: { name: '反馈信息' }, component: () => import('@/views/manager/Feedback.vue'), },
        { path: 'dataAnalysis', meta: { name: '数据统计' }, component: () => import('@/views/manager/DataAnalysis.vue'), },
      ]
    },
    { path: '/login', component: () => import('@/views/Login.vue') },
    { path: '/register', component: () => import('@/views/Register.vue') },
    { path: '/404', component: () => import('@/views/404.vue') },
    { path: '/:pathMatch(.*)', redirect: '/404' }
  ]
})

export default router
