	import http from "axios"
	const APPID = "wxa079e1c44eb5786b"
	const APPSECRET = "7ae1e0c42e255e44af7c899c1f0b543c"


// 更新tonken
function updateToken() {
    return new Promise((resolve, reject) => {
        http.get(`/weixin/cgi-bin/token?grant_type=client_credential&appid=${APPID}&secret=${APPSECRET}`).then(res => {
            resolve(res.data.access_token)
        }).catch(err => {
            reject(err)
        })
    })
}

// 获取Tonken
export function getToKen() {
    return new Promise(async (resolve, reject) => {
        // 从缓存中读取token
        let stroagetoken = uni.getStorageSync('accessToken')
        // 如果缓存中有token，则直接返回缓存中的token
        if (stroagetoken) {
            console.log('缓存获取的token');
            // 返回结果
            resolve(stroagetoken)
        } else {
            // 如果缓存没有token，则走接口重新获取新的token
            let token = await updateToken()
            // 存入到缓存中
            uni.setStorageSync('accessToken', token)
            // 设置定时任务，每隔7000秒更新一次tonken
            setInterval(async () => {
                // 获取新的token
                let token = await updateToken()
                // 存入到缓存中
                uni.setStorageSync('accessToken', token)
            }, 7000000);
            console.log('接口获取的token');
            // 返回结果
            resolve(token)
        }
    })
}