<template>
  <div>
    <el-container class="container">
      <el-aside
        width="200px"
        style="background-color: rgb(238, 241, 246); height: 1000px"
      >
        <el-menu :default-openeds="['1', '2', '3']">
          <el-submenu index="1">
            <template #title><i class="el-icon-message"></i>邮件管理</template>
            <el-menu-item index="1-1" @click="changePage('FetchEmail')"
              >查看邮件</el-menu-item
            >
            <el-menu-item index="1-2" @click="changePage('SendEmail')"
              >发送邮件</el-menu-item
            >
          </el-submenu>

        </el-menu>
      </el-aside>

      <el-container>
        <el-header style="text-align: right; font-size: 12px">
          <el-dropdown>
            <i class="el-icon-setting" style="margin-right: 15px"></i>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item @click="changePage('Login')"
                  >退出登录</el-dropdown-item
                >
              </el-dropdown-menu>
            </template>
          </el-dropdown>
          <span id="stud_name"> {{ mailBox }} </span>
        </el-header>

        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>


<script>
import { useRouter } from "vue-router";
export default {
  data() {
    return {
      mailBox: "",
      authorizationCode:""
    };
  },

  mounted() {
    this.mailBox = this.$root.mailBox;
    this.authorizationCode = this.$root.authorizationCode;
  },
  setup() {
    const router = useRouter();
    const changePage = (destination) => {
      router.push({
        name: destination,
      });
    };
    return {
      changePage,
    };
  },
};
</script>

<style>
#Frame {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.el-header {
  background-color: #b3c0d1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}

html,
body {
  height: 100%;
}

.container {
  height: 100%;
}
</style>