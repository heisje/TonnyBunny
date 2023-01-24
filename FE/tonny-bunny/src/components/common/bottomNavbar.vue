<template lang="">
	<div id="BottomNavbar">
		<ul>
			<li>
				<router-link :to="{ name: 'home' }">
					<div>
						<span class="material-symbols-outlined icon"> home </span>
						<span>홈</span>
					</div>
				</router-link>
			</li>
			<li>
				<router-link :to="{ name: 'board' }">
					<div>
						<span class="material-symbols-outlined icon"> dvr </span>
						<span>커뮤니티</span>
					</div>
				</router-link>
			</li>
			<li>
				<router-link :to="{ name: 'home' }">
					<div>
						<span class="material-symbols-outlined icon"> home </span>
						<span>당근</span>
					</div>
				</router-link>
			</li>
			<li>
				<router-link :to="{ name: 'chat' }">
					<div>
						<span class="material-symbols-outlined icon"> forum </span>
						<span>메세지</span>
					</div>
				</router-link>
			</li>
			<li>
				<router-link :to="{ name: 'mypage' }">
					<div>
						<span class="material-symbols-outlined icon"> account_circle </span>
						<span>내 정보</span>
					</div>
				</router-link>
			</li>
		</ul>
	</div>
</template>
<script>
export default {
	data() {
		return {};
	}
};
</script>
<style lang="scss">
.router-link {
	color: var(--primary-color);
}
.route-active {
	span {
		color: var(--primary-color);
	}
}
#BottomNavbar {
	width: 100vw;
	height: 60px;
	position: fixed;
	bottom: 0px;
	margin: 0px;
	background-color: var(--background-color);
	box-shadow: -1px -1px 5px rgba(0, 0, 0, 0.05);
	z-index: 101;

	ul {
		margin: 0px;
		padding: 0px 5%;
		height: 100%;
		li {
			display: inline-block;
			width: 20%;
			height: 100%;
			position: relative;

			a {
				text-decoration: none;
				div {
					position: absolute;
					width: 100%;
					top: 50%;
					left: 50%;
					text-align: center;
					transform: translate(-50%, -50%);
					span {
						~ span {
							width: 100%;
							text-align: center;
							display: block;
						}
					}
				}
			}
		}
	}
}
.material-symbols-outlined {
	font-variation-settings: "FILL" 0, "wght" 400, "GRAD" 0, "opsz" 48;
}
</style>
