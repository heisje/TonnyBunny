import { createRouter, createWebHistory } from "vue-router";

import AppHeader from "@/pages/common/AppHeader";
import AppFooter from "@/pages/common/AppFooter";

// account
import SignUpPage from "@/pages/account/SignUpPage";
import FindIdPage from "@/pages/account/FindIdPage";
import FindPwPage from "@/pages/account/FindPwPage";
import ResetPwPage from "@/pages/account/ResetPwPage";
import AbilityPage from "@/pages/account/AbilityPage";
import AgreePage from "@/pages/account/AgreePage";
import SignUpCreatePage from "@/pages/account/SignUpCreatePage";
import SignUpCompletePage from "@/pages/account/SignUpCompletePage";

// jtonny
import JTonnyWaitingPage from "@/pages/jtonny/JTonnyWaitingPage";
import JTonnyFormPage from "@/pages/jtonny/JTonnyFormPage";
import JTonnyApplyPage from "@/pages/jtonny/JTonnyApplyPage";
import JTonnyMatchingPage from "@/pages/jtonny/JTonnyMatchingPage";

// ytonny
import YTonnyDetailPage from "@/pages/ytonny/YTonnyDetailPage";
import YTonnyUpdatePage from "@/pages/ytonny/YTonnyUpdatePage";
import YTonnyFormPage from "@/pages/ytonny/YTonnyFormPage";
import YTonnyHelperListPage from "@/pages/ytonny/YTonnyHelperListPage";
import YTonnySuccessPage from "@/pages/ytonny/YTonnySuccessPage";
import YTonnyListPage from "@/pages/ytonny/YTonnyListPage";
import YTonnyMatchingPage from "@/pages/ytonny/YTonnyMatchingPage";

// nbunny
import NBunnyDetailPage from "@/pages/nbunny/NBunnyDetailPage";
import NBunnyFormPage from "@/pages/nbunny/NBunnyFormPage";
import NBunnyHelperListPage from "@/pages/nbunny/NBunnyHelperListPage";
import NBunnyListPage from "@/pages/nbunny/NBunnyListPage";
import NBunnyMatchingPage from "@/pages/nbunny/NBunnyMatchingPage";

// notice
import NoticeListPage from "@/pages/notice/NoticeListPage";
import FAQPage from "@/pages/notice/FAQPage";
import WithdrawalPage from "@/pages/notice/WithdrawalPage";

// board
import BoardPage from "@/pages/board/BoardPage";
import BoardDetailPage from "@/pages/board/BoardDetailPage";
import BoardCreatePage from "@/pages/board/BoardCreatePage";
import BoardUpdatePage from "@/pages/board/BoardUpdatePage";

// live
import OnAirPage from "@/pages/live/OnAirPage";
import LivePage from "@/pages/live/LivePage";
import LiveClosePage from "@/pages/live/LiveClosePage";

// chat
import ChatPage from "@/pages/chat/ChatPage";
import ChatDetailPage from "@/pages/chat/ChatDetailPage";
import NBunnyQuotationForm from "@/components/chat/NBunnyQuotationForm";
import YTonnyQuotationForm from "@/components/chat/YTonnyQuotationForm";
import QuotationDetailPage from "@/pages/chat/QuotationDetailPage";

// mypage
import InfoPage from "@/pages/mypage/InfoPage";
import InfoUpdatePage from "@/pages/mypage/InfoUpdatePage";
import CarrotPage from "@/pages/mypage/CarrotPage";
import HistoryPage from "@/pages/mypage/HistoryPage";
import HistoryDetailPage from "@/pages/mypage/HistoryDetailPage";
import ProfilePage from "@/pages/mypage/ProfilePage";
import ProfileUpdatePage from "@/pages/mypage/ProfileUpdatePage";
import PushAlarmPage from "@/pages/mypage/PushAlarmPage";
import SchedulePage from "@/pages/mypage/SchedulePage";
import FavoriteListPage from "@/pages/mypage/FavoriteListPage";
import BlockListPage from "@/pages/mypage/BlockListPage";
import HelperChangePage from "@/pages/mypage/HelperChangePage";

// home
import HomePage from "@/pages/home/HomePage";
import HelperHomePage from "@/pages/home/HelperHomePage.vue";
import IntroPage from "@/pages/home/IntroPage";
// import BunnyPage from "@/pages/home/BunnyPage";
import TonnyPage from "@/pages/home/TonnyPage";
import MyPage from "@/pages/home/MyPage";
import NoticePage from "@/pages/home/NoticePage";
import AlertPage from "@/pages/home/AlertPage";

//common
import ReportPage from "@/pages/common/ReportPage";
import ReviewPage from "@/pages/common/ReviewPage";
import SuccessPage1 from "@/pages/common/SuccessPage1";
import SuccessPage2 from "@/pages/common/SuccessPage2";
import NotFoundPage from "@/pages/common/NotFoundPage";

// 컴포넌트 페이지 테스트
import ComponentsTestPage from "@/pages/common/ComponentsTestPage";
import InputTest from "@/components/common/input/InputTest";

export default createRouter({
    history: createWebHistory(),
    linkActiveClass: "route-active",
    linkExactActiveClass: "route-active",
    scrollBehavior() {
        return { top: 0 };
    },

    routes: [
        //account
        {
            path: "/account/signup",
            name: "SignUpPage",
            components: {
                header: AppHeader,
                default: SignUpPage,
                footer: AppFooter,
            },
        },

        {
            path: "/account/findid",
            name: "FindIdPage",
            components: {
                header: AppHeader,
                default: FindIdPage,
                footer: AppFooter,
            },
        },

        {
            path: "/account/findpw",
            name: "FindPwPage",
            components: {
                header: AppHeader,
                default: FindPwPage,
                footer: AppFooter,
            },
            props: true,
        },

        {
            path: "/account/resetpw/:userSeq",
            name: "ResetPwPage",
            components: {
                header: AppHeader,
                default: ResetPwPage,
                footer: AppFooter,
            },
            props: true,
        },

        {
            path: "/account/ability/:userSeq",
            name: "AbilityPage",
            components: {
                header: AppHeader,
                default: AbilityPage,
                footer: AppFooter,
            },
            props: true,
        },

        {
            path: "/account/agree/:select",
            name: "AgreePage",
            components: {
                header: AppHeader,
                default: AgreePage,
                footer: AppFooter,
            },
            props: true,
        },

        {
            path: "/account/signup/:select/create",
            name: "SignUpCreatePage",
            components: {
                header: AppHeader,
                default: SignUpCreatePage,
                footer: AppFooter,
            },
            props: true,
        },

        {
            path: "/account/signup/complete",
            name: "SignUpCompletePage",
            components: {
                header: AppHeader,
                default: SignUpCompletePage,
                footer: AppFooter,
            },
            props: true,
        },

        //jtonny
        {
            path: "/jtonny",
            name: "JTonnyWaitingPage",
            components: {
                header: AppHeader,
                default: JTonnyWaitingPage,
                footer: AppFooter,
            },
        },

        {
            path: "/jtonny/form",
            name: "JTonnyFormPage",
            components: {
                header: AppHeader,
                default: JTonnyFormPage,
                footer: AppFooter,
            },
        },

        {
            path: "/jtonny/apply",
            name: "JTonnyApplyPage",
            components: {
                header: AppHeader,
                default: JTonnyApplyPage,
                footer: AppFooter,
            },
        },

        {
            path: "/jtonny/matching",
            name: "JTonnyMatchingPage",
            components: {
                header: AppHeader,
                default: JTonnyMatchingPage,
                footer: AppFooter,
            },
        },

        // ytonny
        {
            path: "/ytonny/:id",
            name: "YTonnyDetailPage",
            components: {
                header: AppHeader,
                default: YTonnyDetailPage,
                footer: AppFooter,
            },
        },

        {
            path: "/ytonny/:id/update",
            name: "YTonnyUpdatePage",
            components: {
                header: AppHeader,
                default: YTonnyUpdatePage,
                footer: AppFooter,
            },
        },

        {
            path: "/ytonny/form",
            name: "YTonnyFormPage",
            components: {
                header: AppHeader,
                default: YTonnyFormPage,
                footer: AppFooter,
            },
        },

        {
            path: "/ytonny/:id/helper",
            name: "YTonnyHelperListPage",
            components: {
                header: AppHeader,
                default: YTonnyHelperListPage,
                footer: AppFooter,
            },
        },

        {
            path: "/ytonny/success",
            name: "YTonnySuccessPage",
            components: {
                header: AppHeader,
                default: YTonnySuccessPage,
                footer: AppFooter,
            },
        },

        {
            path: "/ytonny",
            name: "YTonnyListPage",
            components: {
                header: AppHeader,
                default: YTonnyListPage,
                footer: AppFooter,
            },
        },

        {
            path: "/ytonny/matching",
            name: "YTonnyMatchingPage",
            components: {
                header: AppHeader,
                default: YTonnyMatchingPage,
                footer: AppFooter,
            },
        },

        //nbunny
        {
            path: "/nbunny/:id",
            name: "NBunnyDetailPage",
            components: {
                header: AppHeader,
                default: NBunnyDetailPage,
                footer: AppFooter,
            },
        },

        {
            path: "/nbunny/form",
            name: "NBunnyFormPage",
            components: {
                header: AppHeader,
                default: NBunnyFormPage,
                footer: AppFooter,
            },
        },

        {
            path: "/nbunny/:id/helper",
            name: "NBunnyHelperListPage",
            components: {
                header: AppHeader,
                default: NBunnyHelperListPage,
                footer: AppFooter,
            },
        },

        {
            path: "/nbunny/list",
            name: "NBunnyListPage",
            components: {
                header: AppHeader,
                default: NBunnyListPage,
                footer: AppFooter,
            },
        },

        {
            path: "/nbunny/matching",
            name: "NBunnyMatchingPage",
            components: {
                header: AppHeader,
                default: NBunnyMatchingPage,
                footer: AppFooter,
            },
        },

        //notice
        {
            path: "/notice/list",
            name: "NoticeListPage",
            components: {
                header: AppHeader,
                default: NoticeListPage,
                footer: AppFooter,
            },
        },

        {
            path: "/notice/faq",
            name: "FAQPage",
            components: {
                header: AppHeader,
                default: FAQPage,
                footer: AppFooter,
            },
        },

        {
            path: "/notice/withdraw",
            name: "WithdrawalPage",
            components: {
                header: AppHeader,
                default: WithdrawalPage,
                footer: AppFooter,
            },
        },

        //board
        {
            path: "/board",
            name: "BoardPage",
            components: {
                header: AppHeader,
                default: BoardPage,
                footer: AppFooter,
            },
        },

        {
            path: "/board/:id",
            name: "BoardDetailPage",
            components: {
                header: AppHeader,
                default: BoardDetailPage,
                footer: AppFooter,
            },
        },

        {
            path: "/board/create",
            name: "BoardCreatePage",
            components: {
                header: AppHeader,
                default: BoardCreatePage,
                footer: AppFooter,
            },
        },

        {
            path: "/board/update",
            name: "BoardUpdatePage",
            components: {
                header: AppHeader,
                default: BoardUpdatePage,
                footer: AppFooter,
            },
        },

        //live
        {
            path: "/live",
            name: "OnAirPage",
            components: {
                header: AppHeader,
                default: OnAirPage,
            },
        },

        {
            path: "/live/close/:user",
            name: "LiveClosePage",
            components: {
                header: AppHeader,
                default: LiveClosePage,
                footer: AppFooter,
            },
        },

        //chat
        {
            path: "/chat",
            name: "ChatPage",
            components: {
                header: AppHeader,
                default: ChatPage,
                footer: AppFooter,
            },
        },

        {
            path: "/chat/detail",
            name: "ChatDetailPage",
            components: {
                header: AppHeader,
                default: ChatDetailPage,
                // footer: AppFooter,
            },
        },

        {
            path: "/chat/quotation/ytonny/create",
            name: "YTonnyQuotationForm",
            components: {
                header: AppHeader,
                default: YTonnyQuotationForm,
                footer: AppFooter,
            },
        },

        {
            path: "/chat/quotation/bunny/:clientSeq/create",
            name: "NBunnyQuotationForm",
            components: {
                header: AppHeader,
                default: NBunnyQuotationForm,
                footer: AppFooter,
            },
        },

        {
            path: "/chat/quotation/:id",
            name: "QuotationDetailPage",
            components: {
                header: AppHeader,
                default: QuotationDetailPage,
                footer: AppFooter,
            },
        },

        //mypage
        {
            path: "/mypage/info",
            name: "InfoPage",
            components: {
                header: AppHeader,
                default: InfoPage,
                footer: AppFooter,
            },
        },

        {
            path: "/mypage/info/update",
            name: "InfoUpdatePage",
            components: {
                header: AppHeader,
                default: InfoUpdatePage,
                footer: AppFooter,
            },
        },

        {
            path: "/mypage/carrot",
            name: "CarrotPage",
            components: {
                header: AppHeader,
                default: CarrotPage,
                footer: AppFooter,
            },
        },

        {
            path: "/mypage/history",
            name: "HistoryPage",
            components: {
                header: AppHeader,
                default: HistoryPage,
                footer: AppFooter,
            },
        },

        {
            path: "/mypage/history/:id",
            name: "HistoryDetailPage",
            components: {
                header: AppHeader,
                default: HistoryDetailPage,
                footer: AppFooter,
            },
        },

        {
            path: "/mypage/profile/:id",
            name: "ProfilePage",
            components: {
                header: AppHeader,
                default: ProfilePage,
                footer: AppFooter,
            },
            props: true,
        },

        {
            path: "/mypage/profile/update",
            name: "ProfileUpdatePage",
            components: {
                header: AppHeader,
                default: ProfileUpdatePage,
                footer: AppFooter,
            },
        },

        {
            path: "/mypage/alarm",
            name: "PushAlarmPage",
            components: {
                header: AppHeader,
                default: PushAlarmPage,
                footer: AppFooter,
            },
        },

        {
            path: "/mypage/schedule",
            name: "SchedulePage",
            components: {
                header: AppHeader,
                default: SchedulePage,
                footer: AppFooter,
            },
        },

        {
            path: "/mypage/favorite",
            name: "FavoriteListPage",
            components: {
                header: AppHeader,
                default: FavoriteListPage,
                footer: AppFooter,
            },
        },

        {
            path: "/mypage/block",
            name: "BlockListPage",
            components: {
                header: AppHeader,
                default: BlockListPage,
                footer: AppFooter,
            },
        },

        {
            path: "/mypage/change",
            name: "HelperChangePage",
            components: {
                header: AppHeader,
                default: HelperChangePage,
                footer: AppFooter,
            },
        },

        //home
        {
            path: "/",
            name: "HomePage",
            components: {
                header: AppHeader,
                default: HomePage,
                footer: AppFooter,
            },
        },

        {
            path: "/helper",
            name: "HelperHomePage",
            components: {
                header: AppHeader,
                default: HelperHomePage,
                footer: AppFooter,
            },
        },

        {
            path: "/board",
            name: "BoardPage",
            components: {
                header: AppHeader,
                default: BoardPage,
                footer: AppFooter,
            },
        },

        {
            path: "/intro",
            name: "IntroPage",
            components: {
                header: AppHeader,
                default: IntroPage,
                footer: AppFooter,
            },
        },

        // {
        //     path: "/bunny",
        //     name: "BunnyPage",
        //     components: {
        //         header: AppHeader,
        //         default: BunnyPage,
        //         footer: AppFooter,
        //     },
        // },

        {
            path: "/tonny",
            name: "TonnyPage",
            components: {
                header: AppHeader,
                default: TonnyPage,
                footer: AppFooter,
            },
        },

        {
            path: "/mypage",
            name: "MyPage",
            components: {
                header: AppHeader,
                default: MyPage,
                footer: AppFooter,
            },
        },

        {
            path: "/notice",
            name: "NoticePage",
            components: {
                header: AppHeader,
                default: NoticePage,
                footer: AppFooter,
            },
        },

        {
            path: "/alert",
            name: "AlertPage",
            components: {
                header: AppHeader,
                default: AlertPage,
                footer: AppFooter,
            },
        },

        //common
        {
            path: "/report/:helperSeq",
            name: "ReportPage",
            components: {
                header: AppHeader,
                default: ReportPage,
                footer: AppFooter,
            },
            props: true,
        },

        {
            path: "/review/:helperSeq/:historySeq",
            name: "ReviewPage",
            components: {
                header: AppHeader,
                default: ReviewPage,
                footer: AppFooter,
            },
            props: true,
        },

        {
            path: "/success/1",
            name: "SuccessPage1",
            components: {
                header: AppHeader,
                default: SuccessPage1,
                footer: AppFooter,
            },
        },

        {
            path: "/success/2",
            name: "SuccessPage2",
            components: {
                header: AppHeader,
                default: SuccessPage2,
                footer: AppFooter,
            },
        },

        {
            path: "/test/common",
            name: "ComponentsTestPage",
            components: {
                header: AppHeader,
                default: ComponentsTestPage,
                footer: AppFooter,
            },
        },

        {
            path: "/test/common/input",
            name: "InputTest",
            components: {
                header: AppHeader,
                default: InputTest,
                footer: AppFooter,
            },
        },

        {
            path: "/test/openvidu",
            name: "LivePage",
            components: {
                header: AppHeader,
                default: LivePage,
                footer: AppFooter,
            },
        },

        {
            path: "/:pathMatch(.*)*",
            name: "NotFoundPage",
            components: {
                header: AppHeader,
                default: NotFoundPage,
                footer: AppFooter,
            },
        },
    ],
});
