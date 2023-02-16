<template>
	<div class="container customForm">
		<title-banner title="회원 정보 수정" text="회원 정보를 수정할 수 있습니다" />
		<div style="margin-top: 100px"></div>
		<div class="d-flex align-center">
			<user-profile-img
				style="display: inline-block"
				:profileImagePath="userInfo?.profileImagePath"
				width="80" />
			<h2 class="mx-3">{{ userInfo.nickName }}</h2>
			<medium-btn
				class="ml-3"
				text="프로필 변경"
				color="thin"
				font="active"
				@click="clickInputProfile" />
		</div>

		<!-- {{ profileImg }} -->
		<input
			style="visibility: hidden"
			type="file"
			accept="image/*"
			ref="click"
			@change="insertImage" />

		<div>
			<label for="nickName">닉네임</label>
			<input type="text" id="nickName" name="nickName" v-model="nickName" />
		</div>
		<div v-show="noticeNickName" style="color: red">{{ noticeNickName }}</div>
		<div v-show="successNickName" style="color: green">{{ successNickName }}</div>
		<small-btn
			class="my-3 w-100"
			text="닉네임 수정"
			color="thin"
			font="active"
			@click="changeNickName"></small-btn>

		<hr />

		<div>
			<label for="password1">현재 비밀번호</label>
			<input type="password" id="password1" v-model="password1" />
			<div v-show="noticePassword1" style="color: red">{{ noticePassword1 }}</div>
			<div v-show="successPassword" style="color: green">{{ successPassword }}</div>
		</div>

		<div>
			<label for="password2">새 비밀번호</label>
			<input type="password" id="password2" v-model="password2" />
			<div v-show="noticePassword2" style="color: red">{{ noticePassword2 }}</div>
		</div>
		<div>
			<label for="password3">새 비밀번호 확인</label>
			<input type="password" id="password3" v-model="password3" />
			<div v-show="noticePassword3" style="color: red">{{ noticePassword3 }}</div>
		</div>
		<medium-btn
			class="my-3 w-100"
			text="비밀번호 수정"
			color="thin"
			font="active"
			@click="changePassword"></medium-btn>

		<div style="margin-top: 48px">
			<router-link :to="{ name: 'MyPage' }">
				<smallBtn color="carrot" class="w-100" text="확인"></smallBtn>
			</router-link>
		</div>

		<div style="margin-bottom: 120px"></div>
	</div>
</template>

<script>
import { mapGetters } from "vuex";

import TitleBanner from "@/components/common/TitleBanner.vue";
import MediumBtn from "@/components/common/button/MediumBtn.vue";
import SmallBtn from "@/components/common/button/SmallBtn.vue";
import UserProfileImg from "@/components/common/UserProfileImg.vue";
// import http from "@/common/axios";

export default {
	name: "InfoUpdatePage",
	data() {
		return {
			isOpen: false,
			profileImg: null,
			nickName: "",
			prePassword: "",
			noticeNickName: "",
			noticePassword1: "",
			noticePassword2: "",
			noticePassword3: "",
			password1: "",
			password2: "",
			password3: "",
			successNickName: "",
			successPassword: ""
		};
	},
	components: {
		TitleBanner,
		MediumBtn,
		SmallBtn,
		UserProfileImg
	},
	computed: {
		...mapGetters({
			userInfo: "getUserInfo"
		})
	},

	methods: {
		insertImage(e) {
			this.profileImg = e.target.files[0];
			this.$store.dispatch("putProfileImage", this.profileImg);
		},

		clickInputProfile() {
			this.$refs.click.click();
		},

		changeNickName() {
			if (this.nickName == "") {
				this.noticeNickName = "닉네임을 입력해주세요.";
				return;
			}
			this.$store.dispatch("putUserNickName", this.nickName);
			this.nickName = "";
			this.noticeNickName = "";
			this.successNickName = "닉네임이 변경되었습니다.";
		},

		changePassword() {
			// Validation 요소 추가하기
			if (this.password1 == this.password2) {
				this.noticePassword1 = "현재 비밀번호와 다른 비밀번호를 입력해주세요.";
				this.noticePassword2 = "";
				return;
			}
			if (this.password2 != this.password3) {
				this.noticePassword2 = "비밀번호가 일치하지 않습니다.";
				this.noticePassword1 = "";
				return;
			}

			let payload = {
				password: this.password1,
				newPassword: this.password2,
				checkPassword: this.password3
			};
			this.$store.dispatch("putPassword", payload);
			this.successPassword = "패스워드가 변경되었습니다.";
			this.noticePassword1 = "";
			this.noticePassword2 = "";
		}
	}
};
</script>

<style></style>
