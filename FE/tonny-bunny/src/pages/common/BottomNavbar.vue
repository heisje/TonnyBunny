<template lang="">
	<div id="BottomNavbar">
		<div class="container">
			<ul>
				<li>
					<router-link :to="{ name: 'home' }">
						<span class="material-symbols-outlined icon"> home </span>
						<span>홈</span>
					</router-link>
				</li>
				<li>
					<router-link :to="{ name: 'board' }">
						<span class="material-symbols-outlined icon"> dvr </span>
						<span>커뮤니티</span>
					</router-link>
				</li>
				<li>
					<router-link :to="{ name: 'home' }">
						<span class="material-symbols-outlined icon"> home </span>
						<span>당근</span>
					</router-link>
				</li>
				<li>
					<router-link :to="{ name: 'chat' }">
						<span class="material-symbols-outlined icon"> forum </span>
						<span>메세지</span>
					</router-link>
				</li>
				<li>
					<router-link :to="{ name: 'mypage' }">
						<span class="material-symbols-outlined icon"> account_circle </span>
						<span>내 정보</span>
					</router-link>
				</li>
			</ul>
		</div>
	</div>
</template>
<script>
export default {
	data() {
		return {};
	}
};
</script>
<style lang="scss" scoped>
.router-link {
	color: var(--primary-color);
}

.route-active {
	span {
		color: var(--primary-color);
	}
}

#BottomNavbar {
	position: fixed;
	bottom: 0;
	left: 0;

	width: 100vw;
	height: 60px;
	background-color: var(--background-color);
	box-shadow: 0px -1px -1px 5px rgba(0, 0, 0, 0.05);
	border: 1px solid rgba(0, 0, 0, 0.062);

	display: flex;
	justify-content: center;
	align-items: center;

	z-index: 101;

	ul {
		// width: 100vw;

		display: flex;
		justify-content: space-around;
		align-content: center;

		list-style: none;
		padding: 0;
		margin: 0;

		li {
			a {
				display: flex;
				flex-direction: column;
				align-items: center;

				text-decoration: none;

				span:nth-child(1) {
					font-size: 1.3rem;
				}
				span:nth-child(2) {
					font-size: 0.8rem;
					font-weight: 500;
				}
			}
		}
	}
}

.material-symbols-outlined {
	font-variation-settings: "FILL" 0, "wght" 400, "GRAD" 0, "opsz" 48;
}

@media only screen and (min-device-width: 100px) and (max-device-width: 768px) {
	#BottomNavbar {
		width: 100vw;
		left: 0;
		right: 0;
	}
}

@media (min-width: 768px) {
	#BottomNavbar {
	}
}

@media (min-width: 1200px) {
	#BottomNavbar {
		display: none;
	}
}
</style>
