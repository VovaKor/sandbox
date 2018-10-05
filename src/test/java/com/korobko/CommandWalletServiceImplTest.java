package com.korobko;

import com.korobko.repositories.AccountRepository;
import com.korobko.repositories.Repository;
import com.korobko.services.CommandWalletService;
import com.korobko.services.CommandWalletServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * @author Vova Korobko
 */
public class CommandWalletServiceImplTest {

    @Test
    public void givenPositiveAmount_deposit_correct() {
        AccountRepository
                .getInstance()
                .getAccounts()
                .put(100L, new BigDecimal("10.56"));
        CommandWalletService service = new CommandWalletServiceImpl();
        boolean result = service.deposit(100L, new BigDecimal("2.44"));
        assertTrue(result);
        BigDecimal bigDecimal = AccountRepository
                .getInstance()
                .getAccounts()
                .get(100L);
        assertEquals(0, bigDecimal.compareTo(new BigDecimal("13.00")));
    }

    @Test
    public void givenPositiveAmount_withdraw_correct() {
        AccountRepository
                .getInstance()
                .getAccounts()
                .put(100L, new BigDecimal("10.56"));
        CommandWalletService service = new CommandWalletServiceImpl();
        boolean result = service.withdraw(100L, new BigDecimal("2.44"));
        assertTrue(result);
        BigDecimal bigDecimal = AccountRepository
                .getInstance()
                .getAccounts()
                .get(100L);
        assertEquals(0, bigDecimal.compareTo(new BigDecimal("8.12")));
    }

    @Test
    public void givenAccountRepository_whenDepositParallel_thenCorrect()
            throws Exception {

        List<Integer> sumList = parallelDeposit(
                AccountRepository.getInstance(), 1000);

        assertEquals(1, sumList
                .stream()
                .distinct()
                .count());
        long wrongResultCount = sumList
                .stream()
                .filter(num -> num != 100)
                .count();

        assertEquals(0, wrongResultCount);
    }
    @Test
    public void givenNotThreadSafeAccountRepository_whenDepositParallel_thenNotCorrect() throws Exception {

        List<Integer> sumList = parallelDeposit(NotThreadSafeAccountRepository.getInstance(), 1000);

        long wrongResultCount = sumList
                .stream()
                .filter(num -> num != 100)
                .count();

        assertTrue(wrongResultCount > 0);
    }
    @Test
    public void givenAccountRepository_whenWithdrawParallel_thenCorrect()
            throws Exception {

        List<Integer> sumList = parallelWithdraw(
                AccountRepository.getInstance(), 1000);

        assertEquals(1, sumList
                .stream()
                .distinct()
                .count());
        long wrongResultCount = sumList
                .stream()
                .filter(num -> num != 0)
                .count();

        assertEquals(0, wrongResultCount);
    }
    private List<Integer> parallelDeposit(Repository repository, int executionTimes) throws InterruptedException {
        List<Integer> sumList = new ArrayList<>(1000);
        CommandWalletService service = new CommandWalletServiceImpl();
        for (int i = 0; i < executionTimes; i++) {
                    repository
                    .getAccounts()
                    .put(100L, BigDecimal.ZERO);
            ExecutorService executorService =
                    Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            for (int j = 0; j < 10; j++) {
                executorService.execute(() -> {
                    for (int k = 0; k < 10; k++)
                        service.deposit(100L, BigDecimal.ONE);
                });
            }
            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);
            sumList.add(repository
                    .getAccounts()
                    .get(100L).intValue());
        }
        return sumList;
    }

    private List<Integer> parallelWithdraw(Repository repository, int executionTimes) throws InterruptedException {
        List<Integer> sumList = new ArrayList<>(1000);
        CommandWalletService service = new CommandWalletServiceImpl();
        for (int i = 0; i < executionTimes; i++) {
            repository
                    .getAccounts()
                    .put(100L, new BigDecimal("100.0"));
            ExecutorService executorService =
                    Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            for (int j = 0; j < 10; j++) {
                executorService.execute(() -> {
                    for (int k = 0; k < 10; k++)
                        service.withdraw(100L, BigDecimal.ONE);
                });
            }
            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);
            sumList.add(repository
                    .getAccounts()
                    .get(100L).intValue());
        }
        return sumList;
    }
}