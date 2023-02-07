<template>
	<div class="navBarContainer">
		<div class="navBarWrap">
			<div class="navBar d-md-block d-none">
				<nav class="navbar navbar-white">
					<div class="container-fluid container">
						<div class="d-flex">
							<a class="navbar-brand" href="/">
								<img src="@/assets/logo.png" alt="" width="100" />
							</a>

							<div class="navBarTexts d-flex">
								<ul class="navbar-nav flex-row">
									<li class="nav-item">
										<router-link
											:to="{ name: 'HomePage' }"
											class="nav-link"
											aria-current="page">
											<h2>HOME</h2>
										</router-link>
									</li>

									<li class="nav-item">
										<router-link :to="{ name: 'IntroPage' }" class="nav-link">
											<h2>토니버니란?</h2>
										</router-link>
									</li>

									<li class="nav-item">
										<div class="nav-link" @click="toggleTonnyModalOpen">
											<h2>통역하기</h2>
										</div>
									</li>

									<li class="nav-item">
										<div class="nav-link" @click="toggleBunnyModalOpen">
											<h2>번역하기</h2>
										</div>
									</li>

									<li class="nav-item">
										<router-link :to="{ name: 'BoardPage' }" class="nav-link">
											<h2>커뮤니티</h2>
										</router-link>
									</li>
								</ul>
							</div>
						</div>

						<div class="navBarProfile" v-if="isLogin">
							<router-link :to="{ name: 'AlertPage' }">
								<span class="material-symbols-outlined notification">
									notifications
								</span>
							</router-link>

							<div class="d-flex align-items-center">
								<span class="nickName" @click="openPopOver">
									{{ userInfo?.nickName }}
								</span>
								<img
									src="@/assets/noProfile.png"
									width="40"
									height="40"
									@click="openPopOver" />
							</div>
							<div :class="[isPopOverOpen ? 'd-block' : 'd-none', 'profilePopOver']">
								<div class="popOverArrow"></div>
								<ul>
									<li>
										<router-link
											class="dropdown-item"
											:to="{ name: 'ChatPage' }">
											채팅함
										</router-link>
									</li>
									<li>
										<router-link
											class="dropdown-item"
											:to="{ name: 'ChatPage' }">
											즐겨찾기
										</router-link>
									</li>
									<li>
										<router-link class="dropdown-item" :to="{ name: 'MyPage' }">
											마이페이지
										</router-link>
									</li>
									<li>
										<router-link
											class="dropdown-item"
											:to="{ name: 'NoticePage' }">
											설정
										</router-link>
									</li>
									<li>
										<div class="dropdown-item" @click="logout">로그아웃</div>
									</li>
								</ul>
							</div>
						</div>
						<div v-else>
							<medium-btn
								class="me-2"
								text="로그인"
								color="outline"
								font="main"
								@click="toggleLoginModal"></medium-btn>
							<medium-btn
								text="회원가입"
								color="carrot"
								font="white"
								@click="clickSignUpBtn"></medium-btn>
						</div>
					</div>
				</nav>
			</div>

			<div class="navBarMobile d-lg-none">
				<nav class="navbar navbar-white">
					<div class="container-fluid container px-3">
						<button
							class="navbar-toggler"
							type="button"
							data-bs-toggle="offcanvas"
							data-bs-target="#offcanvasDarkNavbar"
							aria-controls="offcanvasDarkNavbar">
							<span class="navbar-toggler-icon"></span>
						</button>
						<a class="navbar-brand" href="/">
							<img src="@/assets/logo.png" alt="" width="100" />
						</a>
						<div class="d-flex">
							<div v-if="isLogin">
								<router-link :to="{ name: 'AlertPage' }">
									<span class="material-symbols-outlined notification">
										notifications
									</span>
								</router-link>
							</div>
							<div v-else>
								<medium-btn
									class="me-2"
									text="로그인"
									color="outline"
									font="main"
									@click="toggleLoginModal"></medium-btn>
							</div>
						</div>
						<div
							class="offcanvas offcanvas-start text-bg-light"
							tabindex="-1"
							id="offcanvasDarkNavbar"
							aria-labelledby="offcanvasDarkNavbarLabel">
							<div class="offcanvas-header">
								<h4 class="offcanvas-title" id="offcanvasDarkNavbarLabel">MENU</h4>
								<button
									type="button"
									class="btn-close btn-close-light"
									data-bs-dismiss="offcanvas"
									aria-label="Close"></button>
							</div>

							<div class="offcanvas-body">
								<ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
									<li class="nav-item" data-bs-dismiss="offcanvas">
										<router-link
											:to="{ name: 'HomePage' }"
											class="nav-link"
											aria-current="page">
											<h2>HOME</h2>
										</router-link>
									</li>

									<li class="nav-item" data-bs-dismiss="offcanvas">
										<router-link :to="{ name: 'IntroPage' }" class="nav-link">
											<h2>토니버니란?</h2>
										</router-link>
									</li>

									<li class="nav-item" data-bs-dismiss="offcanvas">
										<div class="nav-link" @click="toggleTonnyModalOpen">
											<h2>통역하기</h2>
										</div>
									</li>

									<li class="nav-item" data-bs-dismiss="offcanvas">
										<div class="nav-link" @click="toggleBunnyModalOpen">
											<h2>번역하기</h2>
										</div>
									</li>

									<li class="nav-item" data-bs-dismiss="offcanvas">
										<router-link :to="{ name: 'BoardPage' }" class="nav-link">
											<h2>커뮤니티</h2>
										</router-link>
									</li>

									<li class="nav-item dropdown" v-if="isLogin">
										<a
											class="nav-link dropdown-toggle"
											href="#"
											role="button"
											data-bs-toggle="dropdown"
											aria-expanded="false">
											<img
												src="@/assets/noProfile.png"
												width="40"
												height="40" />
											<h3>닉네임</h3>
										</a>

										<ul class="dropdown-menu">
											<li data-bs-dismiss="offcanvas">
												<router-link
													class="dropdown-item"
													:to="{ name: 'ChatPage' }">
													채팅함
												</router-link>
											</li>
											<li data-bs-dismiss="offcanvas">
												<router-link
													class="dropdown-item"
													:to="{ name: 'ChatPage' }">
													즐겨찾기
												</router-link>
											</li>
											<li data-bs-dismiss="offcanvas">
												<router-link
													class="dropdown-item"
													:to="{ name: 'MyPage' }">
													마이페이지
												</router-link>
											</li>
											<li data-bs-dismiss="offcanvas">
												<router-link
													class="dropdown-item"
													:to="{ name: 'NoticePage' }">
													설정
												</router-link>
											</li>

											<li>
												<hr class="dropdown-divider" />
											</li>

											<li data-bs-dismiss="offcanvas">
												<a class="dropdown-item" @click="logout"
													>로그아웃</a
												>
											</li>
										</ul>
									</li>
									<div v-else>
										<medium-btn
											data-bs-dismiss="offcanvas"
											class="mt-3 w-100"
											text="로그인"
											color="outline"
											font="main"
											@click="toggleLoginModal"></medium-btn>
										<medium-btn
											data-bs-dismiss="offcanvas"
											class="mt-2 w-100"
											text="회원가입"
											color="carrot"
											@click="clickSignUpBtn"></medium-btn>
									</div>
								</ul>
							</div>
						</div>
					</div>
				</nav>
			</div>
		</div>
	</div>
</template>

<script>
import { mapGetters } from "vuex";

import MediumBtn from "@/components/common/button/MediumBtn.vue";

export default {
	name: "AppHeader",

	components: {
		MediumBtn
	},

	data() {
		return {
			isPopOverOpen: false
		};
	},

	computed: {
		...mapGetters({ isLoginModalOpen: "getIsLoginModalOpen" }),
		...mapGetters({ isLogin: "getIsLogin" }),
		...mapGetters({ userInfo: "getUserInfo" })
	},

	methods: {
		// 마이페이지 pop over
		openPopOver(e) {
			e.preventDefault();
			this.isPopOverOpen = !this.isPopOverOpen;
		},

		// login btn click -> login modal 띄우기
		toggleLoginModal(e) {
			e.preventDefault();
			this.$store.commit("TOGGLE_LOGIN_MODAL");
		},

		// tonny nav click -> tonny modal 띄우기
		toggleTonnyModalOpen(e) {
			e.preventDefault();
			this.$store.commit("TOGGLE_TONNY_MODAL");
		},

		// bunny nav click -> tonny modal 띄우기
		toggleBunnyModalOpen(e) {
			e.preventDefault();
			this.$store.commit("TOGGLE_BUNNY_MODAL");
		},

		// 회원가입 btn click -> sign up page
		clickSignUpBtn(e) {
			e.preventDefault();
			this.$router.push("/account/signup");
		},

		// 로그아웃
		logout() {
			this.$store.dispatch("logout");
		}
	}
};
</script>

<style lang="scss" scoped>
.notification {
	cursor: pointer;
	font-size: 1.6rem;
	font-variation-settings: "FILL" 0, "wght" 300;
	margin: 0;
	padding: 0;
	transition: all 0.13s;

	&:hover {
		color: var(--primary-color);
		font-variation-settings: "FILL" 1;
	}
}

.navBarWrap {
	position: fixed;
	top: 0;
	left: 0;
	width: 100vw; // height: 500px;
	z-index: 100;
}
.navBar {
	.navbar-brand {
		margin-right: 20px;
	}
	.navbar-nav {
		padding: 0 16px;
		display: flex;
		align-items: center;

		h2 {
			color: var(--main-color);
			font-weight: 500;
			font-size: 1rem;
			margin-right: 24px;
			// transition: all 0.13s;

			&:hover {
				// font-weight: 500;
				// text-decoration: underline;
				color: var(--primary-color);
			}
		}
	}
	.navBarProfile {
		cursor: pointer;
		display: flex;
		align-items: center;
		justify-content: center;

		> span:nth-child(1) {
			margin-right: 8px;
		}

		.nickName {
			margin-right: 12px;

			a {
				font-size: 1rem;
				font-weight: 500;
				color: var(--main-color);
				text-decoration: none;
			}
			&:hover {
				text-decoration: underline;
			}

			span {
				font-size: 1rem;
			}
		}

		.profilePopOver {
			position: absolute;
			top: 70px;
			margin-right: -6px;
			// right: 10px;
			width: 120px;
			padding: 12px 16px;

			background-color: #fff;
			border: 1px solid rgba(0, 0, 0, 0.08);
			box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.08);
			z-index: 99;

			ul {
				padding: 0;
				margin: 0;

				li {
					list-style: none;
					margin-bottom: 6px;
					// opacity: 0.8;

					border-bottom: 1px solid var(--light-color);
					// transition: all 0.13s;

					&:hover {
						// font-weight: 600;
						background-color: var(--thin-color);
					}

					a {
						color: var(--main-color);
						font-size: 1rem;
						padding: 2px 0 0 2px;
					}
				}
			}

			.popOverArrow {
				position: absolute;
				top: -7px;
				right: 10px;
				width: 7px;
				height: 7px;
				padding: 7px;
				background-color: #fff;
				border: 1px solid rgba(0, 0, 0, 0.08);
				transform: rotate(45deg);
				border-bottom: none;
				border-right: none;
			}
		}
	}
}
.navbar {
	background-color: var(--background-color);
	box-shadow: 1px 1px 3px 3px rgba(0, 0, 0, 0.03);
	// margin-bottom: 54px;
	padding: 8px 0;

	z-index: 100;

	.nav-link {
		color: var(--main-color);
		cursor: pointer;
	}

	.navbar-toggler,
	.btn-close {
		border: none;
		box-shadow: none;
	}
}

.dropdown {
	background-color: var(--light-color);
	border-radius: 8px;
	padding: 4px 8px;
	margin-top: 12px;

	.dropdown-toggle {
		display: flex;
		align-items: center;
		background-color: transparent;
		border: none;
		// justify-content: space-between;

		img {
			margin-left: 6px;
			margin-right: 10px;
		}

		&:hover {
			border: none;
		}
	}

	.dropdown-menu {
		background-color: transparent;
		margin-bottom: 8px;
		border: none;

		h3 {
			&:hover {
				color: none;
			}
		}

		.dropdown-item {
			font-size: 1rem;
		}
	}
}

.navBarMobile {
	background-color: var(--background-color);
	cursor: default;

	.nav-link {
		h2 {
			font-size: 1.1rem;
			font-weight: 500;
		}

		&:hover {
			color: var(--primary-color);
		}
	}

	.offcanvas {
		padding: 16px;
	}
}

.active {
	// font-weight: 500 !important;
	// text-decoration: underline;
}

@media (min-width: 756px) {
	.navbar {
		height: 80px;
	}
	.navBarContainer {
		height: 80px;
	}
}
@media (max-width: 755px) {
	.navbar {
		height: 72px;
		width: 100vw;
	}
	.navBarContainer {
		height: 72px;
	}
}
</style>
