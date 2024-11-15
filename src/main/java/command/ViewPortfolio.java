package command;

import credit.*;
import portfolio.UserPortfolio;

import static main.Main.logger;

public class ViewPortfolio implements Command{
    private UserPortfolio portfolio;
    public ViewPortfolio(UserPortfolio portfolio) {
        this.portfolio = portfolio;
    }

    @Override
    public void execute() {

        if (portfolio.getPortfolioCredits().isEmpty()) {
            logger.warn("Портфоліо порожнє.\n");
            return;
        }

        logger.info("Ви ще можете відкрити - {} кредитів", portfolio.getMaxCreditLimit() - portfolio.getCurrentCreditLimit());

        logger.info("Ваше портфоліо кредитів:");
        for (Credit credit : portfolio.getPortfolioCredits()) {

            if (credit instanceof PersonalCredit) {
                logger.info("\nКредит від " + ((PersonalCredit) credit).getLenderName());
                logger.info("Номер кредиту: "+credit.getCreditIndex());
                logger.info("Сума: " + credit.getAmount() + " | Відсоткова ставка: " + credit.getInterestRate() + "% | Термін: " + credit.getTerm() + " місяців\n");
            }
            if (credit instanceof BankCredit) {
                logger.info("\nКредит від банку " + ((BankCredit) credit).getBankName());
                logger.info("Кредит: "+((BankCredit) credit).getCreditName());
                logger.info("Номер кредиту: "+credit.getCreditIndex());
                logger.info("Сума: " + credit.getAmount() + " | Відсоткова ставка: " + credit.getInterestRate() + "% | Термін: " + credit.getTerm() + " місяців\n");
            }

        }
    }
}
