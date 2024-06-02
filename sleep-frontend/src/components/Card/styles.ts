import styled from "styled-components";


export const CardArticle = styled.article`
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

export const CardHeader = styled.header`
  font-size: 16px;
  margin: 20px auto;
`;

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
