module.exports = {
    publicPath: "./", // 构建好的文件输出到哪里

    devServer: {
        open: process.platform === "darwin",

        disableHostCheck: false,

        host: "127.0.0.1",

        port: 8989,

        https: false,

        hotOnly: false, // See https://github.com/vuejs/vue-cli/blob/dev/docs/cli-service.md#configuring-proxy

        proxy: null // string | Object

        // before: app => {}
    },
};