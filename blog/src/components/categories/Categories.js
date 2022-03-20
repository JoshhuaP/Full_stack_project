export default function Categories(props) {

    const categoriesElements = props.data.map(i => <div key={i.id} className="categories__item" >
        <h2 className="category__name">{i.name}</h2>
        <div className="categories__buttons">
        <button className="category__showArticles" onClick={event => props.showArticles(event, i.id)}>Voir les articles</button>
        <button className="category__deleteButton" onClick={event => props.deleteCategory(event, i.id)}>Delete</button>
        </div>
    </div>)

    return (
        <div className="categories">
            <h1 className='categories__title'>Categories</h1>
            <div className='categories__container'>
                {categoriesElements}
            </div>
        </div>
    )
}