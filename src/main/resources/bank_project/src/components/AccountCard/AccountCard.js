import Button from "../Button/Button";
import { deleteAccount } from "../../helpers/http/account";
import { clearItem, isReload } from "../../Redux/Reducer/AppReducer/appActions";
import { useDispatch, useSelector } from "react-redux";
import styles from "./AccountCard.module.css";

const AccountCard = ({ account }) => {
  const dispatch = useDispatch();
  const { reload } = useSelector((store) => ({
    reload: store.isReload,
  }));
  const { id, number, currency, balance } = account;
  return (
    <div className={styles.wrapper}>
      <p>
        number:{number}, currency: {currency},balance: {balance}
      </p>
      <Button
        onClick={() => {
          deleteAccount(id)
            .then((res) => {
              res.status === 200 && dispatch(isReload(!reload));
            })
            .catch((e) => {
              console.log(e);
            });
        }}
      >
        x
      </Button>
    </div>
  );
};
export default AccountCard;
