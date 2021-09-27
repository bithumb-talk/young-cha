const ROUTE = Object.freeze({
  MAIN: {
    PATH: '/',
  },
  SIGNIN: {
    PATH: '/signin',
  },
  SIGNUP: {
    PATH: '/signup',
  },
  MYPAGE: {
    PATH: '/mypage/:username',
  },
  COIN: {
    PATH: '/coin/:coinname',
  },
});

export default ROUTE;
