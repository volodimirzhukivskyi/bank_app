export const ADD_SHOPS_FAILURE = "ADD_PRODUCT_FAILURE";
export const ADD_SHOPS_SUCCESS = "ADD_PRODUCT_SUCCESS";
export const ADD_PRODUCT_CART = "ADD_SHOPPING_CART";
export const DELETE_FROM_SHOPPING = "DELETE_FROM_SHOPPING";
export const CHANGED_COUNTER_ITEM = "CHANGED_COUNTER";
export const CLEAR_ITEM = "CLEAR_ITEM";
export const IS_OPEN_ACCOUNT = "IS_OPEN_ACCOUNT";
export const IS_RELOAD = "IS_RELOAD";
export const IS_LOADING = "IS_LOADING";
export const IS_OPEN = "IS_OPEN";
export const IS_CREATE_ACCOUNT = "IS_CREATE_ACCOUNT";
export const COUNT_PAGE = "COUNT_PAGE";
export const addShopsFailure = (error) => ({
  type: ADD_SHOPS_FAILURE,
  payload: {
    error,
  },
});
export const isOpenAccount = (boolean) => ({
  type: IS_OPEN_ACCOUNT,
  payload: boolean,
});
export const countPage = (integer) => ({
  type: COUNT_PAGE,
  payload: integer,
});

export const isReload = (boolean) => ({
  type: IS_RELOAD,
  payload: boolean,
});
export const isCreateAccountFunc = (boolean) => ({
  type: IS_CREATE_ACCOUNT,
  payload: boolean,
});

export const isOpenFunc = (obj) => ({
  type: IS_OPEN,
  payload: obj,
});
export const addShopsSuccess = (payload) => ({
  type: ADD_SHOPS_SUCCESS,
  payload: payload,
});
export const isLoadingFunc = (payload) => ({
  type: IS_LOADING,
  payload: payload,
});
export const addProductCart = (prodCard) => ({
  type: ADD_PRODUCT_CART,
  payload: prodCard,
});
export const changedCountItem = (filterArr) => ({
  type: CHANGED_COUNTER_ITEM,
  payload: filterArr,
});
export const clearItem = () => ({
  type: CLEAR_ITEM,
});

export const deleteFromShopping = (itemId) => ({
  type: DELETE_FROM_SHOPPING,
  payload: itemId,
});
