<template>
  <div>
    <div id="course_list">
      <el-table :data="emailData" style="width: 100%" row-key="id" border>
        <el-table-column prop="index" label="编号" width="40"></el-table-column>
        <el-table-column prop="from" label="发件人" width="140"></el-table-column>
        <el-table-column prop="subject" label="主题" width="150"></el-table-column>
        <el-table-column prop="content" label="内容"
                         show-overflow-tooltip=true
        ></el-table-column>
        <el-table-column prop="date" label="日期" width="180"></el-table-column>
        <el-table-column fixed="right" label="操作" width="100">
          <template #default="scope">
            <el-button @click="dropEmail(scope.row)" type="text"
            >删除邮件
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      emailData: [],
    };
  },
  methods: {
    getEmail() {
      console.log("正在获取emails");
      let that = this;
      let url = "http://localhost:60541/query?type=query&username=" +
          that.$root.mailBox + "&password=" + that.$root.authorizationCode;
      axios
          .get(url, {})
          .then(function (response) {
            console.log(response.data);
            that.emailData = response.data.data;
          })
          .catch(function (error) {
            console.log(error);
          });
    },
    dropEmail(row) {
      console.log(row);
      let that = this;
      this.$confirm("你是否确认要删除该封邮件？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
          .then(() => {
            let url = "http://localhost:60541/delete?type=delete&username=" +
                that.$root.mailBox + "&password=" + that.$root.authorizationCode + "&deleteIndex=" + row.index;
            axios
                .get(url, {})
                .then(function (response) {
                  console.log(response);
                  that.getEmail()
                })
                .catch(function (error) {
                  console.log(error);
                });

            this.$message({
              type: "success",
              message: "删除成功",
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
    this.getEmail()
  },
  setup() {
  },
};
</script>

<style>
</style>