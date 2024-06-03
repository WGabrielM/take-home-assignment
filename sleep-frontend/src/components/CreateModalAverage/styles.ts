import styled from "styled-components";

export const ModalAverageOverlay = styled.div`
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

export const CardAverageArticle = styled.article`
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: space-around;

  background-color: lightgray;
  border-radius: 8px;
  box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;

  padding: 10px;

  height: 275px;
  width: 222px;
`;

export const CardAverageHeader = styled.header`
  font-size: 16px;
  margin: 20px auto;
`;

export const CardAverageFellings = styled.div`
    display: flex;
    flex-direction: column;
    flex-wrap: wrap;
    height: 70px;
    gap: 25px;
    align-content: center;
`;

export const FellingGood = styled.div`
    display: flex;
    align-items: center;
    flex-direction: column;

    font-weight: bold;
    color: green;
`
export const FellingOk = styled.div`
    display: flex;
    align-items: center;
    flex-direction: column;

    font-weight: bold;
    color: black;
`
export const FellingBad = styled.div`
    display: flex;
    align-items: center;
    flex-direction: column;

    font-weight: bold;
    color: red;
`

export const STATUS_COLORS = {
    GOOD: "green",
    OK: "black",
    BAD: "red",
} as const;

interface StatusProps {
    statusColor: keyof typeof STATUS_COLORS;
}

export const CardFelling = styled.p<StatusProps>`
  b {
    color: ${(props) => props.theme[STATUS_COLORS[props.statusColor]]};
  }
`;
