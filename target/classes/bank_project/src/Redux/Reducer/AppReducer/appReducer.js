import {
  ADD_SHOPS_SUCCESS,
  ADD_PRODUCT_CART,
  ADD_SHOPS_FAILURE,
  IS_LOADING,
  CHANGED_COUNTER_ITEM,
  CLEAR_ITEM,
  DELETE_FROM_SHOPPING,
  IS_OPEN_ACCOUNT,
  IS_RELOAD,
  IS_OPEN,
  IS_CREATE_ACCOUNT,
  COUNT_PAGE,
} from "./appActions";

const INITIAL_STATE = {
  shops: [],
  error: null,
  isLoading: true,
  isReload: true,
  productsCart: [],
  openAccount: null,
  isOpen: { boolean: false, activeId: null },
  isCreateAccount: false,
  countPage: 0,
};
const appReducer = (state = INITIAL_STATE, action) => {
  switch (action.type) {
    case ADD_SHOPS_SUCCESS:
      return {
        ...state,
        error: null,
        shops: [...action.payload],
      };
    case COUNT_PAGE: {
      return {
        ...state,
        countPage: action.payload,
      };
    }
    case ADD_PRODUCT_CART:
      return {
        ...state,
        productsCart: state.productsCart.find(
          (item) => item.id === action.payload.id
        )
          ? state.productsCart.map((item) => {
              if (item.id === action.payload.id) {
                return { ...item, count: item.count + 1 };
              } else {
                return item;
              }
            })
          : [...state.productsCart, { ...action.payload, count: 1 }],
      };
    case CHANGED_COUNTER_ITEM:
      return {
        ...state,
        productsCart: action.payload,
      };
    case ADD_SHOPS_FAILURE:
      return {
        ...state,
        error: action.payload.error,
      };
    case IS_OPEN_ACCOUNT:
      return {
        ...state,
        openAccount: action.payload,
      };
    case IS_CREATE_ACCOUNT:
      return {
        ...state,
        isCreateAccount: action.payload,
      };
    case IS_LOADING:
      return {
        ...state,
        isLoading: action.payload,
      };
    case IS_OPEN:
      return {
        ...state,
        isOpen: action.payload,
      };
    case IS_RELOAD:
      return {
        ...state,
        isReload: action.payload,
      };
    case DELETE_FROM_SHOPPING:
      return {
        ...state,
        productsCart: state.productsCart.filter((n) => n.id !== action.payload),
      };
    case CLEAR_ITEM:
      return {
        ...state,
        productsCart: [],
      };
    default:
      return state;
  }
};
export default appReducer;
