<template>
  <div>
    <span>收件人邮箱</span>
    <br/>
    <el-input v-model="tousername" class="in_select" size="medium" clearable>
    </el-input>

    <br/>
    <br/>

    <span>邮件主题</span>
    <br/>
    <el-input v-model="subject" class="in_select" size="medium" clearable>
    </el-input>

    <br/>
    <br/>

    <span>邮件内容</span>
    <br/>
    <el-input type="textarea" v-model="content" style="margin: 20px;width: 40%"
              :autosize="{ minRows: 4, maxRows: 8}" clearable>
    </el-input>


    <br/>
    <br/>

    <el-button
        type="primary"
        icon="el-icon-search"
        @click="sendEmail"
        class="searchButton"
        size="small"
    >发送
    </el-button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      tousername: "",
      subject: "",
      content: "",
    };
  },
  methods: {
    sendEmail() {
      let that = this;

      this.$confirm("你是否确认要发送该封邮件？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
          .then(() => {
            let url = "http://localhost:60541/send?type=send&username=" +
                that.$root.mailBox + "&password=" + that.$root.authorizationCode + "&tousername=" +
                that.tousername + "&subject=" + that.subject + "&content=" + that.content;
            axios
                .get(url, {})
                .then(function (response) {
                  console.log(response);
                })
                .catch(function (error) {
                  console.log(error);
                });

            this.$message({
              type: "success",
              message: "发送成功",
            });
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消操作",
            });
          });
    },
  },

  created() {
  },

  setup() {
  },
};
</script>

<style>
.searchButton {
  margin-left: 200px;
  margin-bottom: 10px;
}

.in_select {
  width: 30%;
  margin: 20px;
}

.el-table .gray-row {
  color: #cecece !important;
  pointer-events: none;
}
</style>