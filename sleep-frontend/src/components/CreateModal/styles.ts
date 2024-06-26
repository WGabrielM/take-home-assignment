import styled from "styled-components";

export const ModalOverlay = styled.div`
    position: fixed;
    inset: 0;
    background-color: rgba(0,0,0,0.4);
    overflow: hidden;
    height: 100vh;
    width: 100vw;

    display: flex;
    align-items: center;
    justify-content: center;

    z-index: 999;
`

export const ModalBody = styled.div`
    background-color: white;
    padding: 24px;
    height: 60%;
    width: 60%;
    border-radius: 24px;

    display: flex;
    align-items: flex-start;
    flex-direction: column;
    justify-content: space-between;

    h2 {
        font-size: 32px;
    }

`

export const FormInput = styled.form`
    width: calc(100% - 24px);

    input {
        padding: 12px;
        border: 2px solid #c6c5c5c5;
        color: rgba(0,0,0,0.9);
        font-size: 18px;
        line-height: 24px;
        border-radius: 12px;
        width: 100%;

        margin-bottom: 12px;
    }

    label {
        color: #242424;
        font-weight: 600;
        margin-bottom: 8px;
        font-size: 18px;
    }
    
`

export const ButtonSubmit = styled.button`
    position: initial;
    width: 100%;
    margin-top: 32px;

    &:hover {
        background-color: #3a44f8;
        transform: scale(1);
    }

`
