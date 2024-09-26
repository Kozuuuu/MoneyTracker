<template>
  <body>
    <div class="container">
      <div class="card">
        <h3>Add Record</h3>
        <div>
          <h5>Details</h5>
        </div>
      </div>
    </div>

    <div class="card">
      <h2>Add Financial Record</h2>
      <form id="transaction-form">
        <div class="form-group">
          <label for="type">Type</label>
          <select id="type" name="type">
            <option value="income">Income</option>
            <option value="expense">Expense</option>
          </select>
        </div>

        <div class="form-group">
          <label for="amount">Amount</label>
          <input
            type="number"
            id="amount"
            name="amount"
            min="7000"
            max="30000"
            step="500"
            value="7000"
            required
          />
        </div>

        <div class="form-group">
          <label for="savings">Savings</label>
          <input
            type="number"
            id="savings"
            name="savings"
            min="1000"
            max="30000"
            step="500"
            value="5000"
            required
          />
        </div>

        <div class="form-group">
          <label for="savingsDescription">Savings Description</label>
          <input
            type="text"
            id="savingsDescription"
            name="savingsDescription"
          />
        </div>

        <div class="form-group">
          <label for="expenses">Expenses</label>
          <input
            type="number"
            id="expenses"
            name="expenses"
            min="1000"
            max="30000"
            step="500"
            value="5000"
            required
          />
        </div>

        <div class="form-group">
          <label for="expensesDescription">Expenses Description</label>
          <input
            type="text"
            id="expensesDescription"
            name="expensesDescription"
          />
        </div>

        <div class="form-group">
          <label for="category">Category</label>
          <input type="text" id="category" name="category" />
        </div>

        <!-- <button type="submit.prevent" class="btn">Add Transaction</button> -->
      </form>
    </div>

    <div class="card">
      <h2>View Financial Records</h2>
      <table>
        <thead>
          <tr>
            <th>Date</th>
            <th>Type</th>
            <th>Amount</th>
            <th>Category</th>
          </tr>
        </thead>
      </table>
    </div>
  </body>
</template>

<style scoped>
.card {
  background: white;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  padding: 1rem;
  margin-bottom: 1rem;
}
.card h3 {
  margin-top: 0;
  margin-bottom: 0;
  font-size: 28px;
  font-weight: bold;
}
.card h5 {
  margin-top: 0;
  margin-bottom: 0;
  font-size: 130%;
  font-weight: bold;
}
</style>
