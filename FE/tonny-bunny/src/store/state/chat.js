export default {
    chatRoomConntected: false,
    chatRoomInfo: {},
    chatUserSeq: "",
    chatRoomList: new Map(),

    // Noti or Quotation 과 연결되는 채팅
    sendUrlMessage: false, // true이면 chatRoom 입장할 때 urlMessage를 채팅방에서 send함
    urlMessage: {}, // {urlPage="채팅을 눌렀을 때 이동할 페이지 이름", urlPageSeq="공고 or 견적서 Seq", message="전달할 메세지"}

    // STOMP socket
    chatStompSocketConnected: false,
    chatStompSocket: "",
    chatSocket: "",
};
