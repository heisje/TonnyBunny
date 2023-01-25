<template>
	<div>
		<div class="navBar d-lg-block d-none">
			<nav class="navbar navbar-white">
				<div class="container-fluid container">
					<div class="d-flex">
						<a class="navbar-brand" href="/">
							<img src="@/assets/logo.png" alt="" width="80" />
						</a>

						<div class="navBarTexts d-flex">
							<ul class="navbar-nav flex-row">
								<li class="nav-item">
									<router-link
										:to="{ name: 'home' }"
										class="nav-link"
										aria-current="page">
										<h2>HOME</h2>
									</router-link>
								</li>

								<li class="nav-item">
									<router-link :to="{ name: 'intro' }" class="nav-link">
										<h2>토니버니란?</h2>
									</router-link>
								</li>

								<li class="nav-item">
									<router-link :to="{ name: 'tonny' }" class="nav-link">
										<h2>통역하기</h2>
									</router-link>
								</li>

								<li class="nav-item">
									<router-link :to="{ name: 'bunny' }" class="nav-link">
										<h2>번역하기</h2>
									</router-link>
								</li>

								<li class="nav-item">
									<router-link :to="{ name: 'board' }" class="nav-link">
										<h2>커뮤니티</h2>
									</router-link>
								</li>
							</ul>
						</div>
					</div>

					<div class="navBarProfile">
						<span class="material-symbols-outlined notification"> notifications </span>
						<div @click="openPopOver">
							<span class="nickName">닉네임 <span>님</span></span>
							<img src="@/assets/noProfile.png" width="40" height="40" />
						</div>
						<div :class="[isPopOverOpen ? 'd-block' : 'd-none', 'profilePopOver']">
							<div class="popOverArrow"></div>
							<ul>
								<li>
									<router-link class="dropdown-item" :to="{ name: 'chat' }">
										채팅함
									</router-link>
								</li>
								<li>
									<router-link class="dropdown-item" :to="{ name: 'chat' }">
										즐겨찾기
									</router-link>
								</li>
								<li>
									<router-link class="dropdown-item" :to="{ name: 'mypage' }">
										마이페이지
									</router-link>
								</li>
								<li>
									<router-link class="dropdown-item" :to="{ name: 'notice' }">
										설정
									</router-link>
								</li>
								<li>
									<router-link class="dropdown-item" to="logout">
										로그아웃
									</router-link>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</nav>
		</div>

		<div class="navBarMobile d-lg-none">
			<nav class="navbar navbar-white">
				<div class="container-fluid container">
					<button
						class="navbar-toggler"
						type="button"
						data-bs-toggle="offcanvas"
						data-bs-target="#offcanvasDarkNavbar"
						aria-controls="offcanvasDarkNavbar">
						<span class="navbar-toggler-icon"></span>
					</button>
					<a class="navbar-brand" href="/">
						<img src="@/assets/logo.png" alt="" width="80" />
					</a>
					<div class="d-flex">
						<span class="material-symbols-outlined notification"> notifications </span>
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
								<li class="nav-item">
									<router-link
										:to="{ name: 'home' }"
										class="nav-link"
										aria-current="page">
										<h2>HOME</h2>
									</router-link>
								</li>

								<li class="nav-item">
									<router-link :to="{ name: 'intro' }" class="nav-link">
										<h2>토니버니란?</h2>
									</router-link>
								</li>

								<li class="nav-item">
									<router-link :to="{ name: 'tonny' }" class="nav-link">
										<h2>통역하기</h2>
									</router-link>
								</li>

								<li class="nav-item">
									<router-link :to="{ name: 'bunny' }" class="nav-link">
										<h2>번역하기</h2>
									</router-link>
								</li>

								<li class="nav-item">
									<router-link :to="{ name: 'board' }" class="nav-link">
										<h2>커뮤니티</h2>
									</router-link>
								</li>

								<li class="nav-item dropdown">
									<a
										class="nav-link dropdown-toggle"
										href="#"
										role="button"
										data-bs-toggle="dropdown"
										aria-expanded="false">
										<img src="@/assets/noProfile.png" width="40" height="40" />
										<h3>닉네임</h3>
									</a>

									<ul class="dropdown-menu">
										<li>
											<router-link
												class="dropdown-item"
												:to="{ name: 'chat' }">
												채팅함
											</router-link>
										</li>
										<li>
											<router-link
												class="dropdown-item"
												:to="{ name: 'chat' }">
												즐겨찾기
											</router-link>
										</li>
										<li>
											<router-link
												class="dropdown-item"
												:to="{ name: 'mypage' }">
												마이페이지
											</router-link>
										</li>
										<li>
											<router-link
												class="dropdown-item"
												:to="{ name: 'notice' }">
												설정
											</router-link>
										</li>

										<li>
											<hr class="dropdown-divider" />
										</li>

										<li>
											<a class="dropdown-item" href="#">로그아웃</a>
										</li>
									</ul>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</nav>
		</div>
	</div>
</template>

<script>
export default {
	name: "AppHeader",

	data() {
		return {
			isPopOverOpen: false
		};
	},

	methods: {
		openPopOver(e) {
			e.preventDefault();
			this.isPopOverOpen = !this.isPopOverOpen;
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
}

.navBar {
	.navbar-brand {
		margin-right: 16px;
	}
	.navbar-nav {
		padding: 0 12px;
		display: flex;
		align-items: center;

		h2 {
			color: var(--main-color);
			font-weight: 400;
			font-size: 1.01rem;
			margin-right: 24px;

			&:hover {
				font-weight: 500;
				// text-decoration: underline;
			}
		}
	}
	.navBarProfile {
		cursor: pointer;
		display: flex;
		align-items: center;
		justify-content: center;

		> span:nth-child(1) {
			margin-right: 3px;
		}

		.nickName {
			margin-right: 12px;
		}

		.profilePopOver {
			position: absolute;
			top: 70px;
			margin-right: -10px;
			// right: 10px;
			width: 120px;
			padding: 12px 16px;

			background-color: #fff;
			border: 1px solid rgba(0, 0, 0, 0.08);
			box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.08);

			ul {
				padding: 0;
				margin: 0;

				li {
					list-style: none;
					margin-bottom: 6px;
					opacity: 0.8;

					&:hover {
						color: var(--main-color);
						opacity: 1;
					}

					border-bottom: 1px solid var(--light-color);
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
	box-shadow: 1px 1px 3px rgba(0, 0, 0, 0.08);
	margin-bottom: 54px;
	padding: 16px 0;

	.nav-link {
		color: var(--main-color);
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
			margin-right: 8px;
		}

		&:hover {
			border: none;
		}
	}

	.dropdown-menu {
		background-color: transparent;
		margin-bottom: 8px;
		border: none;
	}
}

.navBarMobile {
	background-color: var(--background-color);

	.nav-link {
		h2 {
			font-weight: 400;
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

// @media (min-width: 1200px) {
//     .navBarMobile {
//         display: none;
//     }
// }

// @media only screen and (min-device-width: 100px) and (max-device-width: 768px) {
//     .modalCustom {
//         width: 85%;
//     }
// }
</style>
