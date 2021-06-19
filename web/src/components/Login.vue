<template>
  <div id="wholePage">
    <div id="loginPage">
      <div class="infoBox welcome">MUZI邮件代理系统</div>
      <!-- 选择身份 -->
      <div class="chooseIdentity">请选择要登录的邮箱</div>
      <div class="chooseBox">
        <el-radio-group v-model="emailType">
          <el-radio-button label="qq"></el-radio-button>
          <el-radio-button label="163"></el-radio-button>
        </el-radio-group>
      </div>

      <!-- 登录信息填写 -->
      <div class="infoBox">
        <span>邮箱</span>
        <el-input v-model="id" class="in_login" size="medium"> 
          <template #append>@{{emailType}}.com</template>
        </el-input>
      </div>

      <div class="infoBox">
        <span>授权码</span>
        <el-input v-model="authorizationCode" class="in_login" size="medium" show-password>
        </el-input>
      </div>

      <!-- 登录按钮 -->
      <el-button type="primary" @click="login" class="loginButton">
        登录
      </el-button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      emailType: "qq",
      id: "",
      authorizationCode: "",
      verify: "",
    };
  },
  methods: {
    login: function () {
      let that = this;

      that.$root.mailBox = that.id + "@" + that.emailType + ".com";
      that.$root.authorizationCode = that.authorizationCode;
      that.$router.push({
        name: "FetchEmail",
      });

    },
  },
};
</script>

<style>
#loginPage {
  text-align: center;
  border-radius: 20px;
  margin: 150px auto;
  background-color: #dbf8fe;
  width: 400px;
  height: 560px;
}

.chooseBox {
  margin-bottom: 40px;
}

.chooseIdentity {
  font-weight: 500;
  font-size: 16px;
  margin-bottom: 20px;
  margin-top: 30px;
}

.infoBox {
  width: 300px;
  margin: 10px auto;
}

.welcome {
  font-size: 25px;
  font-weight: 700;
  position: relative;
  top: 40px;
  margin-bottom: 70px;
}

.in_login {
  width: 220px;
  margin: 10px;
}

.loginButton {
  display: block;
  width: 100px;
  margin: 35px auto;
}
</style>