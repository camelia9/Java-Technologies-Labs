import random, string
import matplotlib.pyplot as plt
from requests import post, get
from multiprocessing import Pool, cpu_count
from time import time
from collections import deque


def random_string(size):
    return ''.join(random.choices(string.ascii_uppercase + string.digits, k=size))


def make_request(url, body):
    start = time()
    post(url, data=body)
    return time() - start


def benchmark(number_of_tests, request_maker, url, data_generator):
    results = deque()
    workers = deque()
    total_workers = cpu_count() * 2
    with Pool(total_workers) as pool:
        for _ in range(number_of_tests):
            workers.append(pool.apply_async(
                            func=request_maker, 
                            args=[url, data_generator()], 
                            callback=lambda res: results.append(res)))
        for worker in workers:
            worker.wait()
    return results


if __name__ == '__main__':
    time = list(benchmark(100, make_request, 'http://localhost:8080/OCA6/benchmark', 
                lambda : {
                    'str1': random_string(random.randint(8, 15)), 
                    'str2': random_string(random.randint(8, 15))
                }
            ))
    
    plt.ylabel('seconds')
    plt.plot(time)
    plt.show()