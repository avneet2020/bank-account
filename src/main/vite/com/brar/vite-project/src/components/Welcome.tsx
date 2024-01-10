import { useState, FormEvent } from "react";
import LoginBody from "../interfaces/LoginBody";
import "./Welcome.css";

interface Props {
  userInfo: LoginBody;
  url: string;
}

function Welcome({ userInfo, url }: Props) {
  const [isDeposit, setIsDeposit] = useState(true);
  const [processing, setProcessing] = useState("Process Transaction");
  const [errorValue, setErrorValue] = useState("");
  const [successfulTransaction, setSuccessfulTransaction] = useState(false);

  const setProcessingButtonToDefault = () =>
    setProcessing("Process Transaction");

  const handleRadioChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setIsDeposit(e.target.value === "Deposit");
  };

  const handleTransaction = async (e: FormEvent) => {
    e.preventDefault();
    setProcessing("Processing...");
    const formData = new FormData(e.target as HTMLFormElement);
    let amountValue = formData.get("number");
    let amount =
      typeof amountValue === "string" ? parseInt(amountValue) : undefined;
    amount = parseInt(amountValue as string);

    if (amount === undefined) {
      setErrorValue("Invalid input");
      setProcessingButtonToDefault();
      return;
    }
    if (!isDeposit && amount > userInfo.balance) {
      setErrorValue("Insufficient funds");
      setProcessingButtonToDefault();
      return;
    }
    if (amount < 0) {
      setErrorValue("Enter a positive number");
      setProcessingButtonToDefault();
      return;
    }
    if (!isDeposit) {
      amount = Math.round(-amount);
    }

    const body = {
      balance: (userInfo.balance + amount).toString(),
    };

    const information: void = await fetch(
      `${url}updateBalance/${userInfo.id}`,
      {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(body),
      }
    )
      .then((res) => res.json())
      .then((response) => {
        if (response) {
          setProcessingButtonToDefault();
          setSuccessfulTransaction(true);
          userInfo.balance += amount || 0;
        }
      })
      .catch((err) => {
        setErrorValue("Error during fetch");
        console.error("Error during fetch", err);
      });
  };

  return (
    <div className="entered">
      <h1>Welcome, {userInfo.firstName}!</h1>
      <form onSubmit={handleTransaction}>
        <div className="btn-group">
          <input
            type="radio"
            name="Deposit"
            className="btn-check"
            checked={isDeposit}
            onChange={handleRadioChange}
            value="Deposit"
            id="btnradio1"
          />
          <label htmlFor="btnradio1" className="btn btn-outline-success">
            Deposit
          </label>
          <input
            type="radio"
            name="Withdraw"
            className="btn-check"
            checked={!isDeposit}
            onChange={handleRadioChange}
            value="Withdraw"
            id="btnradio2"
          />
          <label htmlFor="btnradio2" className="btn btn-outline-danger">
            Withdraw
          </label>
        </div>
        <div className="input-group mb-3">
          <span className="input-group-text">$</span>
          <input name="number" type="text" className="form-control" />
          <span className="input-group-text">.00</span>
        </div>
        {successfulTransaction && (
          <div
            className="alert alert-success alert-dismissible fade show"
            role="alert"
          >
            Transaction Success!
            <button
              type="button"
              className="btn-close"
              onClick={() => setSuccessfulTransaction(false)}
              data-bs-dismiss="alert"
            />
          </div>
        )}
        {errorValue !== "" && (
          <div
            className="alert alert-warning alert-dismissible fade show"
            role="alert"
          >
            {errorValue}
            <button
              type="button"
              className="btn-close"
              onClick={() => setErrorValue("")}
              data-bs-dismiss="alert"
            />
          </div>
        )}
        <div className="bottom-row">
          <button type="submit" className="btn btn-primary">
            {processing}
          </button>
          <h3>Balance: ${userInfo.balance}</h3>
        </div>
      </form>
    </div>
  );
}

export default Welcome;
