## Requirements
* Python 2.7.x
  * Libraries
    * `nltk-3.2.2`, `numpy-1.11.3+mkl-cp27`, `scikit_learn-0.15.1-cp27`, `scipy-0.18.1-cp27`, `pattern-2.6`
      * Installation - open the command line and run
    
      `pip install -r requirements.txt`
    * Stanford CoreNLP models
      * Installation - download it from [here](https://bitbucket.org/FrancescoGiak/libraries/src/c12dd03d919f5952eea0952b7f4577e18e9b492b/Desktop/Final/lib/stanford-corenlp-models-current.jar?at=master&fileviewer=file-view-default), then move the jar file into the `lib/` subfolder.
* Java 8+

## Usage

### Training a new model for emotion classification
```
train.sh -i file.csv -d delimiter [-g] -e emotion 
```
where:
* `-i file.csv`: the input file coded in **UTF-8 without BOM**, containing the corpus for the training; the format of the input file is specified [here](https://github.com/collab-uniba/Emotion_and_Polarity_SO/wiki/File-format-for-training-corpus).
* `-d delimiter`: the specific delimite rused in the csv file, in {`comma`, `semicolon`}
* `-g`: extract bigrams and unigrams (mandatory on the first run; extraction can be skipped afterwards for the same input file); dictionaries will be stored in `./training_<file.csv>/dictionary/unigramsList_2.txt` and `./training_<file.csv>/dictionary/BigramsList_1.txt` and `./training_<file.csv>/dictionary/unigramsList_1.txt`  and `./training_<file.csv>/dictionary/BigramsList_2.txt`)
* `-e emotion`: the specific emotion for training the model, defined in {`joy`, `anger`, `sadness`, `love`, `surprise`, `fear`}

As a result, the script will generate the following output files:

* The principal folder named `training_<file.csv>/` contains:
   * `liblinear/`:
     * there are two subfolders: `DownSampling/` and `NoDownSampling/`. Each one contains the following files  (note, `IDMODEL` is in `{0,...,7}`):
          * `modelLiblinear_IDMODEL.Rda`
          * `confusion_matrix_model_IDMODEL.txt`
          * `predictions_model_IDMODEL.csv`
          * `trainingSet.csv`
          * `testingSet.csv`
   * `n-gram/`: it is a directory containing the UnigramsList.txt and the BigramsList.txt
   * `idfs/`: contains the idfs computed for UnigramsList.txt, BigramsList.txt , Wordnet Categories (positive, negative, ambigue,neutral)
   * `feature-<emotion>.csv`: it is a file, in csv fomat, containing all the features extracted from the input corpus


### Emotion detection
```
classify.sh -i file.csv -d delimiter -e emotion -m model -f idf -o dictionary [-l]
```
where:
* `-i file.csv`: the input file coded in **UTF-8 without BOM**, containing the corpus to be classified;the format of the input file is specified [here](https://github.com/collab-uniba/Emotion_and_Polarity_SO/wiki/File-format-for-classification-corpus).
* `-d delimiter`: the specific delimite rused in the csv file, in {`comma`, `semicolon`}
* `-e emotion`: the specific emotion to be detected in the file or text, defined in {`joy`, `anger`, `sadness`, `love`, `surprise`, `fear`}
* `-m model`: the model file learned as a result of the training step (e.g., `model-anger.rda`)
* `-f idf`: path to the idf (Inverse Document Frequency) folder containing  the idfs (unigrams, bigrams, positive, negative, neutral, ambiguos) used for the `feature-<emotion>.csv` created at the end of the training task
* `-o dictionary` : path to the dictionary folder containing  UnigramsList.txt and BigramsList.txt used to train the model given in input\n"
* `-l` : if presents , indicates  `<file.csv>` contains the column `label` \n"

As a result, the script will generate the following output files:
* The principal folder named `classification_<file.csv>` contains :
* `predictions-<emotion>.csv` : containing the binary prediction (yes/no) made on each line of the input corpus.
* `confusion_matrix.txt` : this file appears only if the `<file.csv>` contains the column `label`.

The different formats of the output files are specified [here](https://github.com/collab-uniba/Emotion_and_Polarity_SO/wiki/File-format-for-classification-output).

