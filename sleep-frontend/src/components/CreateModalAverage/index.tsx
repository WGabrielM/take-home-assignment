import { useEffect, useState } from "react";
import { format, parseISO } from "date-fns";

import useTakeHomeAverageData from "../../hooks/useTakeHomeAverageData";
import {
  CardAverageArticle,
  CardAverageFellings,
  CardAverageHeader,
  FellingBad,
  FellingGood,
  FellingOk,
  Fellings,
  ModalAverageOverlay,
} from "./styles";

interface ModalProps {
  closeModal(): void;
}

export default function CreateModalAverage({ closeModal }: ModalProps) {
  const { data, isSuccess } = useTakeHomeAverageData();
  console.log(data);

  useEffect(() => {
    if (!isSuccess) return;
    closeModal();
  }, [isSuccess]);

  return (
    <ModalAverageOverlay>
      <CardAverageArticle>
        <CardAverageHeader>
          {data?.initPeriod} to {data?.endPeriod}
        </CardAverageHeader>

        <strong>{data?.sleepHourAverage}</strong>
        <div>
          <span>{data?.timeSleepAverage}</span> - <span>{data?.timeWakeAverage}</span>
        </div>
        <CardAverageFellings>
          <FellingGood>
            <span>Good</span>
            <p>{data?.countGood}</p>
          </FellingGood>
          <FellingOk>
            <span>OK</span>
            <p>{data?.countOk}</p>
          </FellingOk>
          <FellingBad>
            <span>BAD</span>
            <p>{data?.countBad}</p>
          </FellingBad>
        </CardAverageFellings>
      </CardAverageArticle>
    </ModalAverageOverlay>
  );
}
