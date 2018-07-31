import pandas as pd
pd.__version__

cities = pd.Series(['Salem', 'Chennai', 'Bangalore'])
population = pd.Series([12345, 98765, 45678])
pd.DataFrame({'City': cities, 'Populations': population})