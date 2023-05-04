import Button from "../Button/Button";
import styles from "./Modal.module.css"
function Modal(props) {
    const { header, closeButton, text, actions, click } = props;
    return (
        <div className={styles.containerModal} onClick={click}>
            <div
                className={styles.modalWindow}
                tabIndex="1"
                onClick={(e) => {
                    e.stopPropagation();
                }}
            >
                <div className={styles.modalHeader}>
                    <h2>{header}</h2>
                    {closeButton && (
                        <Button
                            className={styles.CloseButton}
                            onClick={click}
                        >
                            X
                        </Button>
                    )}
                </div>
                <div className={styles.modalContent}>{text}</div>
                {actions}
            </div>
        </div>
    );
}

export default Modal;