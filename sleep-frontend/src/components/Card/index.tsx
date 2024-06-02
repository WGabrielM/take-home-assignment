import { format, parseISO } from "date-fns";
import { CardArticle, CardFelling, CardHeader } from "./styles";

interface CardProps {
  date: Date;
  timeBed: string;
  timeWake: string;
  status: string;
  totalTime: string;
}

export default function Card({
  date,
  timeBed,
  timeWake,
  totalTime,
  status,
}: CardProps) {
  const day = format(date, "dd");
  const month = format(date, "MMMM");

  function convertTime(time: string): string {
    const parsedTime = parseISO(time);
    return format(parsedTime, "HH:mm");
  }

  return (
    <CardArticle>
      <CardHeader>
        {month}, {day}th
      </CardHeader>

      <div>
        <strong>{convertTime(timeBed)} pm</strong> -{" "}
        <strong>{convertTime(timeWake)} am</strong>
      </div>

      <p>{totalTime}</p>

      <CardFelling statusColor={status}>
        You felt: <b>{status}</b>
      </CardFelling>
    </CardArticle>
  );
}
