// module.exports = {
//     devServer: {
//         // 사용자 정의 환경 변수에서 VUE_APP_PORT가 있으면 사용하고
//         // 없으면 3000 포트로 개발서버를 실행합니다.
//         // port: process.env.VUE_APP_PORT || 3000
//         // port:  9080,
//         proxy: {
//             '/api': {
//                 // target: 'http://localhost:9080',
//                 target: 'http://35.202.26.74:9080',
//                 changeOrigin: true,
//                 pathRewrite: {
//                     '^/api': ''
//                 }
//             }
//         }
//     }
// }