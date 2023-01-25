<template>
    <div>
        <div class="navBar d-lg-block d-none">
            <nav class="navbar navbar-white">
                <div class="container-fluid container">
                    <div class="d-flex">
                        <a class="navbar-brand" href="/">
                            <img src="@/assets/logo.png" alt="" width="100" />
                        </a>

                        <div class="navBarTexts d-flex">
                            <ul class="navbar-nav flex-row">
                                <li class="">
                                    <router-link
                                        :to="{ name: 'home' }"
                                        class="nav-link active"
                                        aria-current="page">
                                        <h2>HOME</h2>
                                    </router-link>
                                </li>

                                <li class="">
                                    <router-link
                                        :to="{ name: 'intro' }"
                                        class="nav-link active"
                                        aria-current="page">
                                        <h2>토니버니란?</h2>
                                    </router-link>
                                </li>

                                <li class="">
                                    <router-link
                                        :to="{ name: 'tonny' }"
                                        class="nav-link active"
                                        aria-current="page">
                                        <h2>통역하기</h2>
                                    </router-link>
                                </li>

                                <li class="">
                                    <router-link
                                        :to="{ name: 'bunny' }"
                                        class="nav-link active"
                                        aria-current="page">
                                        <h2>번역하기</h2>
                                    </router-link>
                                </li>

                                <li class="">
                                    <router-link
                                        :to="{ name: 'board' }"
                                        class="nav-link active"
                                        aria-current="page">
                                        <h2>커뮤니티</h2>
                                    </router-link>
                                </li>
                            </ul>
                        </div>
                    </div>

                    <div class="navBarProfile">
                        <span class="material-symbols-outlined"> notifications </span>
                        <span>닉네임 <span>님</span></span>
                        <img src="@/assets/noProfile.png" width="40" height="40" />
                    </div>
                </div>
            </nav>
        </div>

        <div class="navBarMobile d-lg-none">
            <nav class="navbar navbar-white bg-light">
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
                        <img src="@/assets/logo.png" alt="" width="100" />
                    </a>
                    <div class="notification">
                        <span class="material-symbols-outlined"> notifications </span>
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
                                class="btn-close btn-close-dark"
                                data-bs-dismiss="offcanvas"
                                aria-label="Close"></button>
                        </div>

                        <div class="offcanvas-body">
                            <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                                <li class="nav-item">
                                    <router-link
                                        :to="{ name: 'home' }"
                                        class="nav-link active"
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
export default {};
</script>

<style lang="scss" scoped>
.notification {
    cursor: pointer;
}

.navBar {
    .navbar-nav {
        padding: 0 12px;
        display: flex;
        align-items: center;

        h2 {
            color: var(--main-color);
            font-weight: 400;
            font-size: 1.1rem;
            margin-right: 16px;
        }
    }
    .navBarProfile {
        cursor: pointer;
        display: flex;
        align-items: center;
        justify-content: center;

        span {
            margin-right: 8px;
        }
    }
}
.navbar {
    background-color: var(--background-color);
    box-shadow: 1px 1px 3px rgba(0, 0, 0, 0.08);
    margin-bottom: 32px;
    padding: 16px;
}

.dropdown {
    background-color: var(--light-color);
    border-radius: 8px;
    padding: 0 8px;
    margin-top: 8px;

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
