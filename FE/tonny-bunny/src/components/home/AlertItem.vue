<template lang="">
    <div class="alertItemWarp" @click="putAlert" v-show="display">
        <!-- <div class="alertItemContent" :class="alertItem.isRead ? 'isRead' : ''"> -->
        <div class="alertItemContent">
            <!-- <svg xmlns="http://www.w3.org/2000/svg" width="6" height="6" viewBox="0 0 16 16">
                <circle cx="8" cy="8" r="8" />
            </svg> -->

            <span class="taskCode">"{{ getTaskCode }}"</span>
            <span class="content">{{ alertItem?.content }}</span>
            <div class="date">
                <span>{{ alertItem?.createdAt }}</span>
            </div>
            <span class="material-symbols-outlined close" @click="deleteAlert"> close </span>
        </div>
    </div>
</template>
<script>
import { mapGetters } from "vuex";
// import SockJS from "sockjs-client";
import http from "@/common/axios";

export default {
    name: "AlertItem",

    props: {
        alertItem: {
            type: Object,
            default: () => {
                return {
                    alertLogSeq: 1,
                    content: "하이루",
                    tonnySityCode: "",
                    createdAt: "2023-02-01T13:12:03",
                    isRead: false,
                    taskCode: "1234",
                    updatedAt: "2023-02-01T13:12:03",
                };
            },
            description: "alert item",
        },
    },
    data() {
        return {
            display: true,
            socket: "",
        };
    },
    computed: {
        ...mapGetters({
            taskCode: "getTaskCode",
        }),

        getTaskCode() {
            let code;
            this.taskCode.forEach((e) => {
                if (e.value == this.alertItem.taskCode) code = e.name;
            });
            return code;
        },
    },

    methods: {
        async deleteAlert() {
            if (this.alertItem.alertLogSeq == -1) {
                // 채팅 삭제용 -> 그냥 REST api를 호출하는게 나을 것
                console.log(
                    `/chat/alert/${this.alertItem.receivedUserSeq}/${this.alertItem.pageSeq}/${this.alertItem.chatAlertLogSeq}`
                );
                let { data } = await http.delete(
                    `/chat/alert/${this.alertItem.receivedUserSeq}/${this.alertItem.pageSeq}/${this.alertItem.chatAlertLogSeq}`
                );
                if (data.resultCode == "SUCCESS") {
                    console.log("채팅 삭제 성공");
                } else {
                    console.log("채팅 삭제 실패");
                }
                // this.socket = new SockJS(http.getUri() + "/chat");
                // this.socket.onopen = () => {
                //     let deleteChat = {
                //         type: "deleteAlert",
                //         userSeq: this.alertItem.receivedUserSeq,
                //         alertLogSeq: this.alertItem.chatAlertLogSeq,
                //     };
                //     console.log("Chat delete request : ", deleteChat);
                //     this.socket.send(JSON.stringify(deleteChat));
                //     this.socket.close();
                //     this.socket.onclose = () => {
                //         console.log(" 채팅 삭제 소켓 삭제 ");
                //     };
                // };
                // 채팅 알람 -> 보이지 않게
                this.display = false;
            } else {
                this.$emit("deleteAlert");
            }
        },
        putAlert(e) {
            e.stopPropagation();
            this.$emit("putAlert");
        },
    },

    created() {
        // console.log(this.alertItem);
    },
};
</script>
<style lang="scss" scoped>
@import "@/scss/variable.scss";
.alertItemWarp {
    cursor: pointer;
    padding: 1rem;

    border-bottom: 1px solid var(--light-color);
    &:hover {
        background: var(--primary-color-20);
    }

    .alertItemContent {
        .content {
            white-space: nowrap;
            text-overflow: ellipsis;
            overflow: hidden;
        }
        .close {
            z-index: 1;
            font-size: 1.4rem;
            font-weight: 300;
            margin-left: 12px;
            &:hover {
                color: var(--active-color);
            }
        }

        display: flex;
        align-items: center;
        justify-content: flex-start;
        svg {
            margin-left: 0.2rem;
            margin-right: 0.5rem;
            fill: var(--primary-color);
        }
        span {
            display: inline-block;
            @extend .h3;
            font-weight: 600;
            color: var(--active-color);
        }

        &.isRead {
            span {
                color: var(--sub-color);
                font-weight: 400;
            }
            svg {
                fill: var(--light-color);
            }
        }

        .date {
            margin-left: auto;
            white-space: nowrap;
            text-align: right;
        }

        .taskCode {
            color: var(--primary-color);
            margin-right: 0.3rem;
        }
    }
}
</style>
